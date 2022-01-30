package nguyenhoanganhkhoa.com.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nguyenhoanganhkhoa.com.myapplication.home.homepage.AccountFragment;
import nguyenhoanganhkhoa.com.myapplication.home.homepage.TransactionFragment;
import nguyenhoanganhkhoa.com.myapplication.home.homepage.HomeFragment;
import nguyenhoanganhkhoa.com.myapplication.home.homepage.WalletFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new TransactionFragment();
            case 2:
                return new WalletFragment();

            case 3:
                return new AccountFragment();
            default: return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
