package com.sdurga.assignments.autocompleteDemo.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.sdurga.assignments.autocompleteDemo.models.viewModels.PlaceViewModel;

import java.util.List;

import sdurga.assignments.com.autocompletedemo.R;

public class PlacePredictionsListAdapter extends BaseAdapter implements Filterable {

    private static final String TAG = PlacePredictionsListAdapter.class.getSimpleName();
    private List<PlaceViewModel> placeViewModelList;
    private IdentityFilter filter = new IdentityFilter();

    private class IdentityFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults(); // Create new Filter Results and return this to publishResults;
            results.values = placeViewModelList;
            results.count = placeViewModelList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private LayoutInflater layoutInflater;

    public PlacePredictionsListAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        if (placeViewModelList != null) {
            return placeViewModelList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (placeViewModelList != null) {
            return placeViewModelList.get(position);
        }
        Log.e(TAG,"getItem() returned a null object");
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlaceViewModel placeViewModel = placeViewModelList.get(position);
        ViewHolder viewHolder =  null;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.place_predictions_list_row,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.addressLine1 = convertView.findViewById(R.id.place_prediction_list_row_addressLine1);
            viewHolder.addressLine2 = convertView.findViewById(R.id.place_prediction_list_row_addressLine2);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(placeViewModel != null){
            viewHolder.addressLine1.setText(placeViewModel.getAddressLine1());
            viewHolder.addressLine2.setText(placeViewModel.getAddressLine2());
        }
        return convertView;
    }

    public void setPlaceViewModelList(List<PlaceViewModel> placeViewModelList) {
        this.placeViewModelList = placeViewModelList;
    }

    //region VIEW HOLDER CLASS

    private static class ViewHolder {
        TextView addressLine1;
        TextView addressLine2;
    }

    // endregion
}
