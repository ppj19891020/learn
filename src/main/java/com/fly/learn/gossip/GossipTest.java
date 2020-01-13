package com.fly.learn.gossip;

import com.codahale.metrics.MetricRegistry;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.gossip.GossipMember;
import org.apache.gossip.GossipService;
import org.apache.gossip.GossipSettings;
import org.apache.gossip.RemoteGossipMember;
import org.junit.Assert;

/**
 * @author: peijiepang
 * @date 2020-01-13
 * @Description:
 */
public class GossipTest {

    public static void main(String[] args)
        throws URISyntaxException, InterruptedException, UnknownHostException {
        String cluster = "cluster";
        GossipSettings settings = new GossipSettings();
        int seedNodes = 3;
        List<GossipMember> startupMembers = new ArrayList<>();
        for (int i = 1; i < seedNodes+1; ++i) {
            URI uri = new URI("udp://" + "127.0.0.1" + ":" + (50000 + i));
            startupMembers.add(new RemoteGossipMember(cluster, uri, i + ""));
        }


        List<GossipService> clients = new ArrayList<>();
        int clusterMembers = 5;
        for (int i = 1; i < clusterMembers+1; ++i) {
            URI uri = new URI("udp://" + "127.0.0.1" + ":" + (50000 + i));
            GossipService gossipService = new GossipService(cluster,  uri, i + "", new HashMap<String,String>(),
                startupMembers, settings, null, new MetricRegistry());
            gossipService.start();
        }

        Thread.sleep(10000);
        for (int i = 0; i < clusterMembers; ++i) {
            Assert.assertEquals(4, clients.get(i).getGossipManager().getLiveMembers().size());
        }

    }
}
