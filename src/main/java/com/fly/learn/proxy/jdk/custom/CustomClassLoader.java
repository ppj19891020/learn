package com.fly.learn.proxy.jdk.custom;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义类加载器
 */
public class CustomClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        String classpth = CustomClassLoader.class.getResource("").getPath();
        File classPathfile = new File(classpth);
        String className = CustomClassLoader.class.getPackage().getName() + "." +name;
        if(null != classPathfile){
            //classpath不为空
            File file = new File(classPathfile, name + ".class");
            FileInputStream fileInputStream = null;
            ByteArrayOutputStream outputStream = null;
            try{
                fileInputStream = new FileInputStream(file);
                outputStream = new ByteArrayOutputStream();
                byte[] buff = new byte[1024];
                int len;
                while((len=fileInputStream.read(buff))!=-1){
                    outputStream.write(buff, 0, len);
                }
                return defineClass(className, outputStream.toByteArray(), 0, outputStream.size());
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                if(null!=fileInputStream){
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(null!=outputStream){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
