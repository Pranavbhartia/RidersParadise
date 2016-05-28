package com.pranav.ridersparadise;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String FIRST_TEXT_VIEW_TEXT = "param1";
    private static final String SECOND_TEXT_VIEW_TEXT = "param2";
    private static final String THIRD_TEXT_VIEW_TEXT = "param3";
    private static final String IMAGE_NAME = "param4";


    private String string1;
    private String string2;
    private String string3;
    private String imageName;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 First textView's text.
     * @param param2 Second textView's text.
     * @param param3 Third textView's text.
     * @param param4 Image on the tab.
     * @return A new instance of fragment MainFragment.
     */
    public static MainFragment newInstance(String param1, String param2, String param3, String param4) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(FIRST_TEXT_VIEW_TEXT, param1);
        args.putString(SECOND_TEXT_VIEW_TEXT, param2);
        args.putString(THIRD_TEXT_VIEW_TEXT, param3);
        args.putString(IMAGE_NAME, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            string1 = getArguments().getString(FIRST_TEXT_VIEW_TEXT);
            string2 = getArguments().getString(SECOND_TEXT_VIEW_TEXT);
            string3 = getArguments().getString(THIRD_TEXT_VIEW_TEXT);
            imageName = getArguments().getString(IMAGE_NAME);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setTextString(string1, string2, string3, view);
        setImage(imageName, view);
        return view;
    }

    /**
     * Use this  method to set the textView's text in
     * this fragment using the provided parameters.
     *
     * @param string1 First textView's text.
     * @param string2 Second textView's text.
     * @param string3 Third textView's text.
     * @param view current View in which the texts have to be applied
     * @return void.
     */
    public void setTextString(String string1, String string2, String string3, View view){
        ((TextView) view.findViewById(R.id.text1)).setText(string1);
        ((TextView) view.findViewById(R.id.text2)).setText(string2);
        ((TextView) view.findViewById(R.id.text3)).setText(string3);
    }

    /**
     * Use this  method to set Image in
     * this fragment using the provided parameters.
     *
     * @param imageName imageResource name, without extension.
     * @param view current View in which the texts have to be applied
     * @return void.
     */
    public void setImage(String imageName, View view){
        String uri = "@drawable/"+imageName;

        int imageResource = getResources().getIdentifier(uri, null, "com.pranav.ridersparadise");

        ImageView imageView= (ImageView)view.findViewById(R.id.image1);
        Drawable res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);    }

}
