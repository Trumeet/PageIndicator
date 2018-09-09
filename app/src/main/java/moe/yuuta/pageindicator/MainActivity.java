package moe.yuuta.pageindicator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.systemui.qr.PageIndicator;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends FragmentActivity {
    private PageIndicator mPageIndicator;
    private float mPageIndicatorPosition;
    private int mNumPages;
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = findViewById(R.id.pager);
        setPageIndicator((PageIndicator) findViewById(R.id.indicator));
        mPager.addOnPageChangeListener(this.mOnPageChangeListener);
        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PageFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return mNumPages;
            }
        });
        Toast.makeText(this, "Tap 'ADD' to add pages!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        addPage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Add").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            addPage();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addPage () {
        if (mPager.getAdapter() == null) {
            // onCreate not finished, just skip
            return;
        }
        mNumPages++;
        mPager.getAdapter().notifyDataSetChanged();
        mPageIndicator.setNumPages(mNumPages);
    }

    public void setPageIndicator(PageIndicator indicator) {
        mPageIndicator = indicator;
        mPageIndicator.setNumPages(mNumPages);
        mPageIndicator.setLocation(mPageIndicatorPosition);
    }

    private final ViewPager.OnPageChangeListener mOnPageChangeListener =
            new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                }

                @Override
                public void onPageScrolled(int position, float positionOffset,
                                           int positionOffsetPixels) {
                    if (mPageIndicator == null) return;
                    mPageIndicatorPosition = position + positionOffset;
                    mPageIndicator.setLocation(mPageIndicatorPosition);
                }
            };
}
