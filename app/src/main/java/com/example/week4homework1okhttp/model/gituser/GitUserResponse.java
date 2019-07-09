
package com.example.week4homework1okhttp.model.gituser;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GitUserResponse implements Parcelable
{

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    public final static Creator<GitUserResponse> CREATOR = new Creator<GitUserResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GitUserResponse createFromParcel(Parcel in) {
            return new GitUserResponse(in);
        }

        public GitUserResponse[] newArray(int size) {
            return (new GitUserResponse[size]);
        }

    }
    ;

    protected GitUserResponse(Parcel in) {
        this.totalCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.incompleteResults = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.items, (com.example.week4homework1okhttp.model.gituser.Item.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public GitUserResponse() {
    }

    /**
     * 
     * @param items
     * @param totalCount
     * @param incompleteResults
     */
    public GitUserResponse(Integer totalCount, Boolean incompleteResults, List<Item> items) {
        super();
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totalCount);
        dest.writeValue(incompleteResults);
        dest.writeList(items);
    }

    public int describeContents() {
        return  0;
    }

}
