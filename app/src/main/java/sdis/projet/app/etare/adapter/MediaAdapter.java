package sdis.projet.app.etare.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


import sdis.projet.app.etare.R;
import sdis.projet.app.etare.activity.MediaActivity;
import sdis.projet.app.etare.model.Media;

/*******************************************************************************
 * Created by Alexandre on 28/5/2020
 * Last modified 28/05/20 10:28
 ******************************************************************************/

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    private ArrayList<Media> mDataset;
    private static String TAG = "MainAdapter";
    MediaActivity mediaActivity = new MediaActivity();

    public MediaAdapter(ArrayList<Media> mDataset){
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Bitmap positionData = ByteToBitMap(mDataset.get(position).getImg_media());
        holder.mImageView.setImageBitmap(positionData);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", ""+ mDataset.get(position).getId_media());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageLoad);
        }
    }

    private Bitmap ByteToBitMap(byte[] image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        Log.d("TAG", "ByteToBitMap: "+bitmap.getConfig());
        return bitmap;
    }

}
