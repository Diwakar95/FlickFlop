package com.example.shailu.flickflop;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by shailu on 10/6/16.
 */

public class GridViewAdapter<I> extends ArrayAdapter<ImageItem> {
     Context context;
    String TAG="FlipFlop";
    int layoutResourceId;
    private ArrayList<ImageItem> data=new ArrayList<ImageItem>();
    ArrayList<AnimatorSet> setRightIn = new ArrayList<AnimatorSet>(20);
    ArrayList<AnimatorSet> setLeftOut = new ArrayList<AnimatorSet>(20);
    private TextView imageTitle,imageOwner;
    private ImageView image;
    Boolean[] flipped = new Boolean[20];



    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<ImageItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View gridView;
        initViews();
        ImageItem item = data.get(position);
        for (int i=0 ;i<20 ;i++)
            flipped[i] = false;
        if (convertView == null) {

            gridView = inflater.inflate(layoutResourceId, parent, false);

                      imageOwner = (TextView) gridView.findViewById(R.id.text1);
                          imageOwner.setText(item.getOwnerName());

                     image = (ImageView) gridView.findViewById(R.id.image);

            Glide.with(context).load(item.getImageUrl())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image);
        } else {
            gridView = convertView;
        }
        final View finalGridView = gridView;
        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"position"+position);
                setRightIn.get(position).setTarget(gridView);
                setLeftOut.get(position).setTarget(gridView);
                if (flipped[position]) {
                    flipped[position] = false;
                    setRightIn.get(position).start();
                    setLeftOut.get(position).start();
                    finalGridView.findViewById(R.id.text1).setVisibility(View.GONE);
                    finalGridView.findViewById(R.id.image).setVisibility(View.VISIBLE);
                } else {
                    flipped[position] = true;
                    setLeftOut.get(position).start();
                    setRightIn.get(position).start();
                    finalGridView.findViewById(R.id.text1).setVisibility(View.VISIBLE);
                    finalGridView.findViewById(R.id.image).setVisibility(View.GONE);

                }

            }
        });



        return gridView;
    }
    private void initViews() {
        for (int i=0 ;i < 20 ; i++) {
            AnimatorSet rightIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_in);
            AnimatorSet leftOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_in);
            setRightIn.add(rightIn);
            setLeftOut.add(leftOut);
        }
    }



}
