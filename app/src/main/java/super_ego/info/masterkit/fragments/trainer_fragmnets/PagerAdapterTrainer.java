package super_ego.info.masterkit.fragments.trainer_fragmnets;

/**
 * Created by Andrey on 19.10.2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterTrainer extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public PagerAdapterTrainer(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new SituationFragment();
            case 1:
                return new UniversalFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}