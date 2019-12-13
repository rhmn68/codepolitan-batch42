package com.rahmanaulia.aplikasikontak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahmanaulia.aplikasikontak.adapter.ContactAdapter
import com.rahmanaulia.aplikasikontak.db.ContactHelper
import com.rahmanaulia.aplikasikontak.helper.MappingHelper
import com.rahmanaulia.aplikasikontak.model.Contact
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_CODE_ADD = 300
        const val REQUEST_CODE_UPDATE = 301
    }

    private lateinit var adapter: ContactAdapter
    private lateinit var contactHelper: ContactHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initContactHelper()
        initAdapterContact()
        onClick()
        loadContactAsync()
    }

    private fun loadContactAsync() {
        GlobalScope.launch(Dispatchers.Main){
            showProgress()
            val deferredContacts = async(Dispatchers.IO){
                val cursor = contactHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            val contacts = deferredContacts.await()
            if (contacts.size > 0){
                hideProgress()
                adapter.listContacts = contacts
            }else{
                adapter.listContacts = ArrayList()
                showDataEmpty()
            }
        }
    }

    private fun showProgress() {
        pbContact.visibility = View.VISIBLE
        rvContact.visibility = View.INVISIBLE
    }

    private fun hideProgress(){
        pbContact.visibility = View.INVISIBLE
        rvContact.visibility = View.VISIBLE
        tvEmpty.visibility = View.INVISIBLE
    }

    private fun showDataEmpty(){
        pbContact.visibility = View.INVISIBLE
        tvEmpty.visibility = View.VISIBLE
        rvContact.visibility = View.INVISIBLE
    }

    private fun initAdapterContact() {
        adapter = ContactAdapter{contact, position ->
            val intent = Intent(this, ContactAddUpdateActivity::class.java)
            intent.putExtra(ContactAddUpdateActivity.EXTRA_CONTACT, contact)
            intent.putExtra(ContactAddUpdateActivity.EXTRA_POSITION, position)
            startActivityForResult(intent, REQUEST_CODE_UPDATE)
        }
        rvContact.layoutManager = LinearLayoutManager(this)
        rvContact.adapter = adapter
    }

    private fun onClick() {
        fabAdd.setOnClickListener {
            val intent = Intent(this, ContactAddUpdateActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD)
        }
    }

    private fun initContactHelper() {
        contactHelper = ContactHelper.getInstance(this)
        contactHelper.open()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE_ADD -> if (resultCode == ContactAddUpdateActivity.RESULT_CODE_ADD){
                val contact = data?.getParcelableExtra<Contact>(ContactAddUpdateActivity.EXTRA_CONTACT)
                if (contact != null){
                    adapter.addItem(contact)
                    hideProgress()
                }

                rvContact.smoothScrollToPosition(adapter.itemCount - 1)
                Toast.makeText(this, "Satu item berhasil di tambah", Toast.LENGTH_SHORT).show()
            }
            REQUEST_CODE_UPDATE ->{
                when(resultCode){
                    ContactAddUpdateActivity.RESULT_CODE_UPDATE ->{
                        val contact = data?.getParcelableExtra<Contact>(ContactAddUpdateActivity.EXTRA_CONTACT)
                        val position = data?.getIntExtra(ContactAddUpdateActivity.EXTRA_POSITION, 0)
                        if (contact != null){
                            position?.let {
                                adapter.updateItem(it, contact)
                                hideProgress()
                            }
                        }
                        position?.let { rvContact.smoothScrollToPosition(it) }
                        Toast.makeText(this, "Satu item berhasil diubah", Toast.LENGTH_SHORT).show()
                    }
                    ContactAddUpdateActivity.RESULT_CODE_DELETE -> {
                        val position = data?.getIntExtra(ContactAddUpdateActivity.EXTRA_POSITION, 0)
                        position?.let {
                            adapter.removeItem(it)
                            hideProgress()
                        }
                        if (adapter.itemCount == 0){
                            showDataEmpty()
                        }
                        Toast.makeText(this, "Satu item berhasil dihapus", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
