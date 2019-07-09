package com.example.week4homework1okhttp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week4homework1okhttp.model.gitrepos.GitReposResponse;

import java.util.List;

public class RepositoriesListAdapter extends RecyclerView.Adapter<RepositoriesListAdapter.ViewHolder>{

    List<GitReposResponse> gitReposResponses;

    public RepositoriesListAdapter(List<GitReposResponse> gitReposResponses) {
        this.gitReposResponses = gitReposResponses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.repository_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GitReposResponse gitReposResponse = gitReposResponses.get(position);

        holder.tvReposDescription.setText(gitReposResponse.getDescription().toString());
        holder.tvReposHtmlUrl.setText(gitReposResponse.getHtmlUrl());
        holder.tvReposCreatedAt.setText(gitReposResponse.getCreatedAt());
        holder.tvReposPushedAt.setText(gitReposResponse.getPushedAt());
        holder.tvReposCloneUrl.setText(gitReposResponse.getCloneUrl());
        holder.tvReposSize.setText(gitReposResponse.getSize());
        holder.tvReposLanguage.setText(gitReposResponse.getLanguage());

        holder.setGitReposResponse(gitReposResponse);
    }

    @Override
    public int getItemCount() {
        return gitReposResponses.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvReposDescription, tvReposHtmlUrl, tvReposCreatedAt, tvReposPushedAt, tvReposCloneUrl, tvReposSize, tvReposLanguage;
        GitReposResponse gitReposResponse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReposDescription = itemView.findViewById(R.id.tvReposDescription);
            tvReposHtmlUrl = itemView.findViewById(R.id.tvReposHtmlUrl);
            tvReposCreatedAt = itemView.findViewById(R.id.tvReposCreatedAt);
            tvReposPushedAt = itemView.findViewById(R.id.tvReposPushedAt);
            tvReposCloneUrl = itemView.findViewById(R.id.tvReposCloneUrl);
            tvReposSize = itemView.findViewById(R.id.tvReposSize);
            tvReposLanguage = itemView.findViewById(R.id.tvReposLanguage);
            itemView.setOnClickListener(this);
        }

        public GitReposResponse getGitReposResponse() {
            return gitReposResponse;
        }

        public void setGitReposResponse(GitReposResponse gitReposResponse) {
            this.gitReposResponse = gitReposResponse;
        }

        @Override
        public void onClick(View view) {
//            Intent intent = new Intent(view.getContext(), EmployeeDetailsActivity.class);
//            intent.putExtra("employee", getEmployee());
//            view.getContext().startActivity(intent);
        }
    }
}
