import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Set;

public class project5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try{
            br = new BufferedReader(
                    new FileReader(args[0]));
            bw = new BufferedWriter(
                    new FileWriter(args[1]));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Node<String>> nodeArrayList = new ArrayList<>();
        String line;
        int count = 0;
        int nodeCount=0;
        int cCount=0;
        HashMap<String, Node<String>> ne = new HashMap<>();
        //ArrayList<Integer> regionCapacity= new ArrayList<>();
        //Long tic = System.currentTimeMillis();
        try{
            while((line =br.readLine())!=null){
                String[] tokens = line.split("\s");
                if (count==0){
                    cCount = Integer.parseInt(tokens[0]);
                    count +=1;
                    continue;
                }
                if(count ==1){
                    Node<String> source = new Node<>("ana");
                    for(int i =0; i<6; i++){
                        Node<String> nodez = new Node<>("r"+i);
                        source.addAdjacentNode(nodez, Integer.parseInt(tokens[i]));
                        ne.put("r"+i,nodez);
                    }
                    ne.put("ana",source);


                }
                if(count>1 && count<=7) {
                    Node<String> node;
                    if (!ne.containsKey(tokens[0])) {
                        node = new Node<>(tokens[0]);
                    } else {
                        node = ne.get(tokens[0]);
                    }
                    if (!nodeArrayList.contains(node)) {
                        nodeArrayList.add(node);
                    }

                    ne.put(tokens[0], node);
                    for (int j = 1; j < tokens.length; j += 2) {
                        Node<String> nodez;
                        if (!ne.containsKey(tokens[j])) {
                            nodez = new Node<>(tokens[j]);
                        } else {
                            nodez = ne.get(tokens[j]);
                        }
                        node.addAdjacentNode(nodez, Integer.parseInt(tokens[j + 1]));
                        if(!nodeArrayList.contains(nodez)){
                            nodeArrayList.add(nodez);

                        }
                        ne.put(tokens[j], nodez);
                    }
                }
                if(count >7) {
                    Node<String> node;
                    if (!ne.containsKey(tokens[0])) {
                        node = new Node<>(tokens[0]);
                    } else {
                        node = ne.get(tokens[0]);
                    }
                    if (!nodeArrayList.contains(node)) {
                        nodeArrayList.add(node);
                    }

                    ne.put(tokens[0], node);
                    for (int j = 1; j < tokens.length; j += 2) {
                        Node<String> nodez;
                        if (!ne.containsKey(tokens[j])) {
                            nodez = new Node<>(tokens[j]);
                        } else {
                            nodez = ne.get(tokens[j]);
                        }
                        node.addAdjacentNode(nodez, Integer.parseInt(tokens[j + 1]));
                        if(!nodeArrayList.contains(nodez)){
                            nodeArrayList.add(nodez);

                        }
                        ne.put(tokens[j], nodez);
                    }
                }
                count +=1;
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        bw.write(fordFulkerson(ne.get("ana"),ne.get("KL"))+"");
        br.close();
        bw.close();



    }
    static boolean  bfs (Node<String> source, Node<String> sink){
        Set<Node<String>> visited= new HashSet<>();
        Queue<Node<String>> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);
        source.SetParent(null);
        while(!queue.isEmpty()){
            Node<String> zort = queue.poll();
            for(Map.Entry<Node<String>, Integer> entry : zort.getAdjacentNodes().entrySet()){
                if(!visited.contains(entry.getKey()))   {
                    if(entry.getKey()==sink){
                        entry.getKey().SetParent(zort);
                        return true;
                    }
                    queue.add(entry.getKey());
                    entry.getKey().SetParent(zort);
                    visited.add(entry.getKey());

                }
            }
        }
        return false;
    }
    public static int fordFulkerson (Node<String> source, Node<String> sink){
        int maxFLow=0;
        while(bfs(source,sink)){
            int pathFlow = Integer.MAX_VALUE;
            for (Node<String> v = sink; v != source; v = v.getParent()) {
                Node<String> u = v.getParent();
                pathFlow = Math.min(pathFlow, u.getAdjacentNodes().get(v));
            }
            for (Node<String> v = sink; v != source; v = v.getParent()) {
                Node<String> u = v.getParent();
                if(u.getAdjacentNodes().get(v)-pathFlow<=0){
                    u.getAdjacentNodes().remove(v);
                }else{
                    u.getAdjacentNodes().put(v, u.getAdjacentNodes().get(v)-pathFlow);
                }
                if(v.getAdjacentNodes().containsKey(u)) {
                    v.getAdjacentNodes().put(u, v.getAdjacentNodes().get(u) + pathFlow);//bu satırın amınakoyım
                }else{
                    v.getAdjacentNodes().put(u, pathFlow);
                }
            }
            maxFLow += pathFlow;
        }
        return maxFLow;
    }




}