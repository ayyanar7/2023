
package com.ag.bta.main.wavelayout.view;

import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

 
import com.ag.bta.main.R;
import com.ag.bta.main.wavelayout.WaveLayout;

public class WaveViewHolder extends ViewHolder {
 
    private WaveLayout mWaveLayout;

    public WaveViewHolder(LayoutInflater inflater, ViewGroup parent, int innerViewResId) {
        super(inflater.inflate(R.layout.wavelayout, parent, false));
        mWaveLayout = (WaveLayout) itemView;

        inflater.inflate(innerViewResId, mWaveLayout, true);
    }

    public void setWaveAngle(int angle) {
        mWaveLayout.setWaveAngle(angle);
    }

    public void setWaveColor(int color) {
        mWaveLayout.setWaveColor(color);
    }

    public void setWaveMaskWidth(float maskWidth) {
        mWaveLayout.setMaskWidth(maskWidth);
    }

    public void setWaveViewHolderBackground(Drawable viewHolderBackground) {
        if (viewHolderBackground != null) {
            setBackground(viewHolderBackground);
        }
    }

    public void setWaveAnimationDuration(int duration) {
        mWaveLayout.setWaveAnimationDuration(duration);
    }

    public void setAnimationReversed(boolean animationReversed) {
        mWaveLayout.setAnimationReversed(animationReversed);
    }

    public void bind() {
        mWaveLayout.startWaveAnimation();
    }

    private void setBackground(Drawable background) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            mWaveLayout.setBackground(background);
        } else {
            mWaveLayout.setBackgroundDrawable(background);
        }
    }
}
