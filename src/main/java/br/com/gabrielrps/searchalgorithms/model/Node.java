package br.com.gabrielrps.searchalgorithms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {

    private int col;
    private int row;
    private Double distance = Double.POSITIVE_INFINITY;
    @JsonProperty("isFinish")
    private boolean isFinish;
    @JsonProperty("isStart")
    private boolean isStart;
    @JsonProperty("isVisited")
    private boolean isVisited;
    @JsonProperty("isWall")
    private boolean isWall;
    private Node previousNode;




}
