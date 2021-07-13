package com.daasuu.mp4compose.filter;

import android.opengl.GLES20;

public class GlFadeOutFilter extends GlFilter {

    private static final String FADEOUT_FRAGMENT_SHADER = "" +
            "precision mediump float;" +
            " varying vec2 vTextureCoord;\n" +
            " \n" +
            " uniform lowp sampler2D sTexture;\n" +
            " uniform highp float uElapsedTime;\n" +
            " uniform vec3 fadeColor;\n" +
            " \n" +
            " void main()\n" +
            " {\n" +
            "     highp vec4 textureColor = texture2D(sTexture, vTextureCoord);\n" +
            "     \n" +
            "     gl_FragColor = vec4(mix(textureColor.rgb, fadeColor, uElapsedTime), textureColor.w);\n" +
            " } ";

    public GlFadeOutFilter() {
        super(DEFAULT_VERTEX_SHADER, FADEOUT_FRAGMENT_SHADER);
    }

    private float[] fadeColor = {0, 0, 0};

    public void setFadeColor(float[] fadeColor) {
        this.fadeColor = fadeColor;
    }

    @Override
    public void onDraw() {
        GLES20.glUniform3f(getHandle("fadeColor"), fadeColor[0], fadeColor[1], fadeColor[2]);
    }
}