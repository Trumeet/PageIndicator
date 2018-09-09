package moe.yuuta.pageindicator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PageFragment extends Fragment {
    private static final String EXTRA_PAGE_INDEX = PageFragment.class.getName() + ".EXTRA_PAGE_INDEX";

    private int index;

    static PageFragment newInstance (int index) {
        Bundle args = new Bundle();
        PageFragment fragment = new PageFragment();

        args.putInt(EXTRA_PAGE_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) return;
        this.index = getArguments().getInt(EXTRA_PAGE_INDEX);
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        ((TextView) view.findViewById(R.id.text_page_digit)).setText(Integer.toString(index));
        return view;
    }
}
