package br.com.gabrielrps.searchalgorithms.controller;

import br.com.gabrielrps.searchalgorithms.algorithms.Dijkstra;
import br.com.gabrielrps.searchalgorithms.constantes.NodeConst;
import br.com.gabrielrps.searchalgorithms.model.Node;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dijkstra")
public class DijkstraController {

    @GetMapping("/heath/check")
    public String getStatus(){
        return "Is working";
    }

    @PostMapping
    public List<Node> dijkstraAlgorithm(@RequestBody List<List<Node>> grid){
        Dijkstra dijkstra = new Dijkstra();

        Node startNode = grid.get(NodeConst.START_NODE_ROW).get(NodeConst.START_NODE_COL);
        Node finishNode = grid.get(NodeConst.FINISH_NODE_ROW).get(NodeConst.FINISH_NODE_COL);

        grid.get(NodeConst.FINISH_NODE_ROW).get(NodeConst.FINISH_NODE_COL).setFinish(true);

        List<Node> ret = dijkstra.dijkstra(grid, startNode, finishNode);

        return ret;
    }

    @PostMapping("/getNodesInShortestPathOrder")
    public List<Node> getNodesInShortestPathOrder(@RequestBody Node finishedNode){
        finishedNode.setFinish(true);
        return Dijkstra.getNodesInShortestPathOrder(finishedNode);
    }


}
