package com.ag.bta.utils.lazyloading;

import androidx.fragment.app.Fragment;

import java.io.Serializable;


public interface FragmentFactory extends Serializable {
    Fragment createFragment();
}
