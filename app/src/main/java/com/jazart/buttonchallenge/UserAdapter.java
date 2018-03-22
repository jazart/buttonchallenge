package com.jazart.buttonchallenge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jazart on 3/21/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private List<User> mUsers;


    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);

        return new UserHolder(v);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {

        holder.bind(mUsers.get(position));
    }


    @Override
    public int getItemCount() {
        return (mUsers == null) ? 0 : mUsers.size();
    }

    public void setList(List<User> users) {
        mUsers = users;
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        private User mUser;
        private TextView mUserNameTextView;
        private TextView mUserIdTextView;
        private TextView mUserEmailTextView;
        private TextView mUserCandidateTextView;

        public UserHolder(View itemView) {
            super(itemView);

            mUserNameTextView = itemView.findViewById(R.id.name);
            mUserIdTextView = itemView.findViewById(R.id.id);
            mUserEmailTextView = itemView.findViewById(R.id.email);
            mUserCandidateTextView = itemView.findViewById(R.id.candidate);

        }

        public void bind(User user) {
            mUser = user;
            mUserNameTextView.setText(MainActivity.sResources.getString(R.string.name, mUser.getName()));
            mUserIdTextView.setText((MainActivity.sResources.getString(R.string.id, mUser.getId())));
            mUserEmailTextView.setText(MainActivity.sResources.getString(R.string.email, mUser.getEmail()));
            mUserCandidateTextView.setText(MainActivity.sResources.getString(R.string.candidate, mUser.getCandidate()));
        }
    }
}
