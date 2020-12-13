package cn.hallowebsite.lib.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

//TODO 待测试
public abstract class AbsRecyclerViewAdapter<T> extends RecyclerView.Adapter<AbsRecyclerViewAdapter.VH>  {

    /**
     * 事件回调监听
     */
    private OnItemClickListener onItemClickListener;

    public AbsRecyclerViewAdapter() {
    }

    public abstract int getLayoutId(int viewType);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.get(parent,getLayoutId(viewType));
    }

    protected abstract T getDataByPosition(int position);

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        convert(holder, getDataByPosition(position), position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos, getDataByPosition(position));
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, pos, getDataByPosition(position));
                }
                //表示此事件已经消费，不会触发单击事件
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return getTotalSize();
    }

    protected abstract int getTotalSize();

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

    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T t);
        void onItemLongClick(View view, int position, T t);
    }

}
