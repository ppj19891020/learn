package com.fly.learn.proxy.jdk.custom;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 自定义代理类
 */
public class CustomProxy {
    public static final String ln = "\r\n";

    public static Object newProxyInstance(CustomClassLoader loader, Class<?>[] interfaces,
                                          CustomInvocationHandler h) throws IllegalArgumentException {
        try {
            String javaName = interfaces[0].getName();
            String javaFileName = javaName.substring(javaName.lastIndexOf(".")+1);
            //1.编译成源码
            String src = generateSrc(javaFileName,interfaces);

            //2.将源码编译成java文件
            String filePath = CustomProxy.class.getResource("").getPath();
            System.out.println(filePath);
            File f = new File(filePath + "$"+javaFileName+"Proxy.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            //3.将java文件编译成class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manage = compiler.getStandardFileManager(
                    null,null,null);
            Iterable iterable = manage.getJavaFileObjects(f);
            JavaCompiler.CompilationTask task = compiler.getTask(
                    null,manage,null,null,null,iterable);
            task.call();
            manage.close();

            //4.将class文件装入jvm
            Class proxyClass=loader.findClass("$"+javaFileName+"Proxy");
            f.delete();

            //5.返回代理类的对象
            Constructor constructor = proxyClass.getConstructor(CustomInvocationHandler.class);
            return constructor.newInstance(h);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 改造源码文件
     *
     * @param interfaces
     * @return
     */
    private static String generateSrc(String javaFileName,Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.fly.learn.proxy.jdk.custom;" + ln);
        sb.append("import java.lang.reflect.Method;" + ln);
        sb.append("public class $"+javaFileName+"Proxy implements " + interfaces[0].getName() + "{" + ln);
        sb.append("private CustomInvocationHandler h;" + ln);
        sb.append("public $"+javaFileName+"Proxy(CustomInvocationHandler h) { " + ln);
        sb.append("this.h = h;" + ln);
        sb.append("}" + ln);
        for (Method m : interfaces[0].getMethods()) {
            sb.append("@Override" + ln);
            sb.append("public " + m.getReturnType().getName() + " "
                    + m.getName() + "(");
            String parame = "";
            String args = "";
            for(int i=0;null != m.getParameterTypes() && i < m.getParameterTypes().length;i++){
                sb.append(m.getParameterTypes()[i].getName()).append(" args").append(i);
                parame += m.getParameterTypes()[i].getName()+".class,";
                args="args"+i+",";
            }
            if(!"".equals(parame)){
                parame = parame.substring(0,parame.length()-1);
                args = args.substring(0,args.length()-1);
            }
            sb.append(") {" + ln);
            sb.append("try{" + ln);
            sb.append("Method m = " + interfaces[0].getName()
                    + ".class.getMethod(\"" + m.getName()
                    + "\",new Class[]{"+parame+"});" + ln);
            sb.append("this.h.invoke(this,m,new Object[]{"+args+"});" + ln);
            sb.append("}catch(Throwable e){" + ln);
            sb.append("e.printStackTrace();" + ln);
            sb.append("}" + ln);
            sb.append("}" + ln);
        }
        sb.append("}" + ln);
        return sb.toString();
    }

}