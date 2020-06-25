package br.com.gabrielrps.searchalgorithms.algorithms;

import br.com.gabrielrps.searchalgorithms.model.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Dijkstra {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Node> dijkstra(List<List<Node>> grid, Node startNode, Node finishNode){
        logger.info(" ----------------- Iniciando o método ------------------------------ ");
        List<Node> visitedNodesInOrder = new ArrayList<>();

        startNode.setDistance(0D);

        List<Node> unvisitedNodes = getAllNodes(grid);

        while(unvisitedNodes.size() != 0){
            sortNodesByDistance(unvisitedNodes);
            Node closestNode = unvisitedNodes.remove(0);

            // If we encounter a wall, we skip it.
            logger.info(" ---------------- Testando se encontrou parede ------------------ ");
            if (closestNode.isWall()) continue;

            // If the closest node is at a distance of infinity,
            // we must be trapped and should therefore stop.
            logger.info("Row: " + closestNode.getRow() + " Col: " + closestNode.getCol() + " Distance: " + closestNode.getDistance());
            if (closestNode.getDistance() == Double.POSITIVE_INFINITY) return visitedNodesInOrder;

            closestNode.setVisited(true);

            visitedNodesInOrder.add(closestNode);

            if (closestNode.equals(finishNode)) return visitedNodesInOrder;

            updateUnvisitedNeighbors(closestNode, grid);

        }
        logger.info(" ----------------- Finalizando o método ------------------------------ ");
        return visitedNodesInOrder;
    }

    private void updateUnvisitedNeighbors(Node node, List<List<Node>> grid) {
        List<Node> unvisitedNeighbors = getUnvisitedNeighbors(node, grid);
        for (Node n: unvisitedNeighbors) {
            n.setDistance(node.getDistance() + 1);
            n.setPreviousNode(node);
        }

    }

    private List<Node> getUnvisitedNeighbors(Node node, List<List<Node>> grid) {
        List<Node> neighbors = new ArrayList<>();
        int col = node.getCol(), row = node.getRow();

        if (row > 0) neighbors.add(grid.get(row - 1).get(col));
        if (row < grid.size() - 1) neighbors.add(grid.get(row + 1).get(col));
        if (col > 0) neighbors.add(grid.get(row).get(col - 1));
        if (col < grid.get(0).size() - 1) neighbors.add(grid.get(row).get(col + 1));

        return neighbors.stream().filter(neighbor -> !neighbor.isVisited()).collect(Collectors.toList());

    }


    public void     sortNodesByDistance(List<Node> unvisitedNodes) {
        unvisitedNodes.sort(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return node1.getDistance().intValue() - node2.getDistance().intValue();
            }
        });
    }

    public List<Node> getAllNodes(List<List<Node>> grid) {
        List<Node> nodes = new ArrayList<>();

        for(List<Node> listaNodes : grid){
            for (Node node: listaNodes) {
                nodes.add(node);
            }
        }

        return nodes;
    }

    // Backtracks from the finishNode to find the shortest path.
    // Only works when called *after* the dijkstra method above.
    public static List<Node> getNodesInShortestPathOrder(Node finishNode) {
        List<Node> nodesInShortestPathOrder = new ArrayList<>();
        Node currentNode = finishNode;

        while (currentNode != null) {
            nodesInShortestPathOrder.add(0, currentNode);
            currentNode = currentNode.getPreviousNode();
        }

        return nodesInShortestPathOrder;
    }

}