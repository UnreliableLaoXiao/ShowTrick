package cn.hallowebsite.lib.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//TODO 待测试
public abstract class AbsRecyclerViewAdapter<T> extends RecyclerView.Adapter<AbsRecyclerViewAdapter.VH>  {

    private List<T> mDataSource;
    protected Context context;
    /**
     * 事件回调监听
     */
    private OnItemClickListener onItemClickListener;

    public AbsRecyclerViewAdapter() {
    }

    public AbsRecyclerViewAdapter(List<T> mDataSource){
        if (mDataSource == null) {
            this.mDataSource = new ArrayList<>();
        }
        this.mDataSource.addAll(mDataSource);
    }

    public void updateDataSource() {
        if (mDataSource == null) {
            this.mDataSource = new ArrayList<>();
        }
        this.mDataSource.clear();
        this.mDataSource.addAll(mDataSource);
    }

    public abstract int getLayoutId(int viewType);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return VH.get(parent,getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        convert(holder, mDataSource.get(position), position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, pos);
                }
                //表示此事件已经消费，不会触发单击事件
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    /**
     * 设置回调监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public abstract void convert(VH holder, T data, int position);

    public static class VH extends RecyclerView.ViewHolder {

        private SparseArray<View> mViews;
        private View mConvertView;

        private VH(View v){
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }

        public static VH get(ViewGroup parent, int layoutId){
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(convertView);
        }

        public <T extends View> T getView(int id){
            View v = mViews.get(id);
            if(v == null){
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T)v;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

}
