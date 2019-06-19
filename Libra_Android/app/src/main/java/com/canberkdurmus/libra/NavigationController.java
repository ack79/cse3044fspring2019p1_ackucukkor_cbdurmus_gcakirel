package com.canberkdurmus.libra;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

class NavigationController {

    static void openTimelineFragment(FragmentManager fragmentManager, String id) {
        FragmentTimeline firstFragment = FragmentTimeline.newInstance(id);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, firstFragment, "FIRST_FRAGMENT");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    static void openSearchFragment(FragmentManager fragmentManager, String id) {
        FragmentSearch firstFragment = FragmentSearch.newInstance(id);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, firstFragment, "FIRST_FRAGMENT");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    static void openAdvertFragment(FragmentManager fragmentManager, String id) {
        FragmentAdvert firstFragment = FragmentAdvert.newInstance(id);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, firstFragment, "FIRST_FRAGMENT");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    static void openLibraryFragment(FragmentManager fragmentManager, String id) {
        FragmentLibrary firstFragment = FragmentLibrary.newInstance(id);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, firstFragment, "FIRST_FRAGMENT");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    static void openProfileFragment(FragmentManager fragmentManager, String id) {
        FragmentProfile firstFragment = FragmentProfile.newInstance(id);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, firstFragment, "FIRST_FRAGMENT");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}
