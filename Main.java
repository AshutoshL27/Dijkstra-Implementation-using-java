package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Edge{
    int targetNode;
    int costFromNode;

    Edge(int targetNode, int cost) {
        this.targetNode = targetNode;
        this.costFromNode = cost;
    }

}

class Graph {
    List<List<Edge>> graph;
    boolean visited[];
    int cost[];

    Graph(int nodes) {
        graph = new ArrayList<>();
        visited = new boolean[nodes];
        cost = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            graph.add(i, new ArrayList<>());
            cost[i] = Integer.MAX_VALUE;
        }
    }

    public void addEdge (int sourceNode, int targetNode, int cost) {
        graph.get(sourceNode).add(new Edge (targetNode, cost));
        //graph.get(targetNode).add(new Edge (sourceNode, cost));
    }

    public int minimumcostBetweenTwoNodes (int source, int destination) {
        if (source == destination)
            return 0;

        PriorityQueue<Edge> minHeap = new PriorityQueue<>((e1, e2) -> e1.costFromNode- e2.costFromNode);

        cost[source] = 0;
        minHeap.add(new Edge(0, 0));

        while (!minHeap.isEmpty()) {
            int v = minHeap.poll().targetNode;
            if (visited[v])
                continue;

            visited[v] = true;

            List<Edge> childList = graph.get(v);
            int m=0;
            for (Edge child: childList) {
                int dist = child.costFromNode;
                int childNode = child.targetNode;
                if (!visited[childNode] && (cost [v] + dist< cost [childNode])) {
                    cost[childNode] = cost[v] + dist;
                    child.costFromNode = cost [v] + dist;
                    child.costFromNode=cost[v]+dist;
                    minHeap.add(child);
                }
            }
        }

        return cost[destination];
    }

}

public class Main{

    public static void main(String[] args) {
        int nodes = 20;

        Graph a = new Graph(nodes);

        a.addEdge(0,15 , 151);
        a.addEdge(0, 1, 71);
        a.addEdge(1, 2, 75);
        a.addEdge(2, 3, 118);
        a.addEdge(2, 15, 140);
        a.addEdge(3, 4, 111);
        a.addEdge(4, 5, 70);
        a.addEdge(5, 6, 75);
        a.addEdge(6, 7, 120);
        a.addEdge(7, 13, 138);
        a.addEdge(7, 14, 146);
        a.addEdge(14,13, 97);
        a.addEdge(14, 15, 80);
        a.addEdge(15, 16, 99);
        a.addEdge(16, 12, 211);
        a.addEdge(12, 8, 90);
        a.addEdge(12, 13, 101);
        a.addEdge(12,11, 85);
        a.addEdge(11, 17, 142);
        a.addEdge(11, 10, 98);
        a.addEdge(10, 9, 86);
        a.addEdge(17, 18, 92);
        a.addEdge(18, 19, 87);

        a.addEdge(15,0 , 151);
        a.addEdge(1, 0, 71);
        a.addEdge(2, 1, 75);
        a.addEdge(3, 2, 118);
        a.addEdge(15, 2, 140);
        a.addEdge(4, 3, 111);
        a.addEdge(5, 4, 70);
        a.addEdge(6, 5, 75);
        a.addEdge(7, 6, 120);
        a.addEdge(13, 7, 138);
        a.addEdge(14, 7, 146);
        a.addEdge(13,14, 97);
        a.addEdge(15, 14, 80);
        a.addEdge(16, 15, 99);
        a.addEdge(12, 16, 211);
        a.addEdge(8, 12, 90);
        a.addEdge(13, 12, 101);
        a.addEdge(11,12 , 85);
        a.addEdge(17, 11, 142);
        a.addEdge(10, 11, 98);
        a.addEdge(9, 16, 86);
        a.addEdge(18, 17, 92);
        a.addEdge(19, 18, 87);

        class GUI extends JFrame  implements ActionListener {
            Font font=new Font("Comic Sans Ms",Font.PLAIN,30);
            JLabel source = new JLabel("Enter source ");
            JLabel dest = new JLabel("Enter destination ");
            JTextField sourceTxt=new JTextField();
            JTextField destTxt=new JTextField();
            JButton btn=new JButton("submit");
            JLabel msg= new JLabel("display msg");

            Container c;

            GUI() {
                c = this.getContentPane();
                c.setLayout(null);
                c.setBounds (250, 150, 800,800);
                c.setLayout(null);

                //image
                ImageIcon icon= new ImageIcon("src/com/company/graph.png");
                JLabel label=new JLabel(icon);
                label.setBounds(50,50,icon.getIconWidth(),icon.getIconHeight());
                source.setBounds(100, 470, 150, 40);
                dest.setBounds(100, 520, 150, 40);
                sourceTxt.setBounds(300,470,250,40);
                destTxt.setBounds(300,520,250,40);
                btn.setBounds(200,590,250,30);
                btn.addActionListener(this);
                c.add(label);
                c.add(source);
                c.add(dest);
                c.add(sourceTxt);
                c.add(destTxt);
                c.add(btn);
            }
            public void actionPerformed(ActionEvent e ){
                if(e.getSource()==btn){
                    int src = Integer.parseInt(sourceTxt.getText())-1;
                    int dest= Integer.parseInt(destTxt.getText())-1;
                    int minCost=a.minimumcostBetweenTwoNodes(src,dest);
                    System.out.println(minCost);
                    msg.setBounds(250,620,150,50);
                    msg.setText("Minimum cost: "+ minCost);
                    c.add(msg);
                }
            }
        }

        GUI gui= new GUI();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}