package com.aruninba.doorconfig.view.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.aruninba.doorconfig.data.mapper.DoorConfigParameter;
import com.aruninba.doorconfig.databinding.ItemConfigBinding;
import com.aruninba.doorconfig.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arun Inba on 19/01/24.
 */
public class ListConfigAdapter extends RecyclerView.Adapter<ListConfigAdapter.ConfigViewHolder> {

    public interface ConfigAdapterListener {
        void onConfigClicked(DoorConfigParameter configResponse);
    }

    private List<DoorConfigParameter> items;
    private final ConfigAdapterListener listener;

    public ListConfigAdapter(ConfigAdapterListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    public void setItems(List<DoorConfigParameter> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public ConfigViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemConfigBinding binding = ItemConfigBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ConfigViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ConfigViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private DoorConfigParameter getItem(int position) {
        return items.get(position);
    }

    public class ConfigViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemConfigBinding binding;
        ConfigViewHolder(ItemConfigBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(int position) {
            DoorConfigParameter configResponse = getItem(position);

            setClickListener(configResponse);
            setTitle(configResponse.getTitle());
            setDoorConfig(configResponse.getPrimaryDoorConfig(),
                    configResponse.getSecondaryDoorConfig(), configResponse.getMyDefault(), configResponse.isCommon());
        }

        private void setTitle(String title) {
            binding.tvConfigTitle.setText(title);
        }

        private void setDoorConfig(String primaryDoorConfig, String secondaryDoorConfig, String defaultConfig,
                                   boolean common) {
            if(!common) {
                binding.tvPrimaryConfig.setVisibility(View.VISIBLE);
                binding.tvCommonConfig.setVisibility(View.INVISIBLE);
                binding.tvSecondaryConfig.setVisibility(View.VISIBLE);
                binding.tvPrimaryConfig.setText(!Constants.isEmpty(primaryDoorConfig) ?
                        primaryDoorConfig : defaultConfig);
                binding.tvSecondaryConfig.setText(!Constants.isEmpty(secondaryDoorConfig) ?
                        secondaryDoorConfig : defaultConfig);
            }else{
                binding.tvPrimaryConfig.setVisibility(View.INVISIBLE);
                binding.tvSecondaryConfig.setVisibility(View.INVISIBLE);
                binding.tvCommonConfig.setVisibility(View.VISIBLE);
                binding.tvCommonConfig.setText(!Constants.isEmpty(primaryDoorConfig) ?
                        primaryDoorConfig : defaultConfig);
            }
        }

        private void setClickListener(DoorConfigParameter movie) {
            itemView.setTag(movie);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onConfigClicked((DoorConfigParameter) view.getTag());
        }
    }
}
