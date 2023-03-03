
package com.ag.bta.main.wavelayout.view;

import android.graphics.drawable.Drawable;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
 

public class WaveAdapter extends Adapter<WaveViewHolder> {
 
    private int mItemCount;
    private int mLayoutReference;
    private int mWaveAngle;
    private int mWaveColor;
    private int mWaveDuration;
    private float mWaveMaskWidth;
    private boolean isAnimationReversed;
    private Drawable mWaveItemBackground;

    @Override
    public WaveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        WaveViewHolder waveViewHolder = new WaveViewHolder(inflater, parent, mLayoutReference);
        waveViewHolder.setWaveColor(mWaveColor);
        waveViewHolder.setWaveAngle(mWaveAngle);
        waveViewHolder.setWaveMaskWidth(mWaveMaskWidth);
        waveViewHolder.setWaveViewHolderBackground(mWaveItemBackground);
        waveViewHolder.setWaveAnimationDuration(mWaveDuration);
        waveViewHolder.setAnimationReversed(isAnimationReversed);

        return waveViewHolder;
    }

    @Override
    public void onBindViewHolder(WaveViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setMinItemCount(int itemCount) {
        mItemCount = itemCount;
    }

    public void setWaveAngle(int WaveAngle) {
        this.mWaveAngle = WaveAngle;
    }

    public void setWaveColor(int WaveColor) {
        this.mWaveColor = WaveColor;
    }

    public void setWaveMaskWidth(float maskWidth) {
        this.mWaveMaskWidth = maskWidth;
    }

    public void setWaveItemBackground(Drawable WaveItemBackground) {
        this.mWaveItemBackground = WaveItemBackground;
    }

    public void setWaveDuration(int mWaveDuration) {
        this.mWaveDuration = mWaveDuration;
    }

    public void setLayoutReference(int layoutReference) {
        this.mLayoutReference = layoutReference;
    }

    public void setAnimationReversed(boolean animationReversed) {
        this.isAnimationReversed = animationReversed;
    }
}
