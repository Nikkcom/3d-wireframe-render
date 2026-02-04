package me.niko.models;

public class ModelFace {
    private final int[] vertexIndices;

    public ModelFace(int... vertexIndices) {
        this.vertexIndices = vertexIndices;
    }

    public int[] getVertexIndices() {
        return vertexIndices;
    }
}
