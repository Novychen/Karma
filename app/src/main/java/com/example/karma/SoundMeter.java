package com.example.karma;

import android.media.MediaRecorder;

import java.io.IOException;

/**
 * Handles the Microphone (& Voice recording) of the device
 */
public class SoundMeter {

    private MediaRecorder mRecorder;

    /**
     * starts the recorder (It won't write a file for the audio input)
     * @throws IOException
     */
    public void start() throws IOException {
        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null/");
            mRecorder.prepare();
            mRecorder.start();
        }
    }

    /**
     * stops the recorder
     */
    public void stop(){
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    /**
     * gets the current maximum amplitude
     * @return the current maximum amplitude
     */
    public double getAmplitude() {
        if (mRecorder != null) { return (mRecorder.getMaxAmplitude()); }
        else { return -1; }
    }


}
