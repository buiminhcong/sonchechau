package com.example.buicong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CauThuAdapter extends RecyclerView.Adapter<CauThuAdapter.CauThuViewHoler> {

    private List<CauThu> cauThuList;
    private Context context;
    public interface OnMyItemClickListener{
        void doSomething(int position);
    }
    private OnMyItemClickListener onMyItemClickListener;

    public void setOnMyItemClickListener(OnMyItemClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }

    public CauThuAdapter(List<CauThu> cauThuList, Context context) {
        this.cauThuList = cauThuList;
        this.context = context;
    }

    @NonNull
    @Override
    public CauThuViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new CauThuViewHoler(view);
    }

    //for
    @Override
    public void onBindViewHolder(@NonNull CauThuViewHoler holder, int position) {
            CauThu c = cauThuList.get(position);
            holder.img.setImageResource(c.getImg());
            holder.ten.setText(c.getName());
            holder.ngaysinh.setText(c.getNgaySinh());
            String vt = c.getViTri().trim();
            String s[] = vt.split(",");

            for(int i=0; i<s.length; i++){
                if(s[i].trim().equals("tien dao")  ){
                    holder.cb1.setChecked(true);
                }
//                else {
//                    holder.cb1.setChecked(false);
//                }
                if(s[i].trim().equals("tien ve") )
                    holder.cb2.setChecked(true);

//                else
//                    holder.cb2.setChecked(false);
                if(s[i].trim().equals("hau ve")  )
                    holder.cb3.setChecked(true);

//                else
//                    holder.cb3.setChecked(false);
            }
            holder.btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cauThuList.remove(position);
                    notifyDataSetChanged();
                }
            });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onMyItemClickListener.doSomething(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return cauThuList.size();
    }

    class CauThuViewHoler extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView ten, ngaysinh;
        private CheckBox cb1;
        private CheckBox cb2;
        private CheckBox cb3;
        private CardView cardView;
        private Button btnXoa;

        public CauThuViewHoler(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.item_img);
            cardView = itemView.findViewById(R.id.cardview);
            ten = itemView.findViewById(R.id.item_ten);
            cb1 = itemView.findViewById(R.id.item_tiendao);
            cb2 = itemView.findViewById(R.id.item_tienve);
            cb3 = itemView.findViewById(R.id.item_hauve);
            btnXoa = itemView.findViewById(R.id.btnXoa);
            ngaysinh = itemView.findViewById(R.id.item_NgaySinh);


        }
    }
    public void addCT(CauThu c){
        cauThuList.add(c);
        notifyDataSetChanged();
    }
    public void updateCauThu(int position, CauThu e){
        cauThuList.set(position, e);
        notifyDataSetChanged();
    }

}
