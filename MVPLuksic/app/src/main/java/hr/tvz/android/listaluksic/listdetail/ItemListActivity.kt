package hr.tvz.android.listaluksic.listdetail

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.listaluksic.R

class ItemListActivity : AppCompatActivity(), ItemListFragment.Callbacks {

    var mTwoPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(findViewById<FrameLayout>(R.id.item_detail_container) != null) {
            mTwoPane = true
        } else {
            mTwoPane = false
            var itemListFragment = ItemListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.item_list_container, itemListFragment)
                .commit()
        }
    }

    override fun onItemSelected(id: String?) {
        var arguments = Bundle()
        arguments.putString(ItemDetailFragment().ARG_ITEM_ID, id)
        var detailFragment = ItemDetailFragment()
        detailFragment.arguments = arguments
        var fragmentTransaction = supportFragmentManager.beginTransaction()

        if (mTwoPane) {
            fragmentTransaction
                .replace(R.id.item_detail_container, detailFragment)
                .commit()
        } else {
            fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.item_list_container, detailFragment)
                .commit()
        }

    }
}