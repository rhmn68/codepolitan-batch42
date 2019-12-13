package com.rahmanaulia.aplikasikontak

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.rahmanaulia.aplikasikontak.db.ContactHelper
import com.rahmanaulia.aplikasikontak.db.DatabaseContract
import com.rahmanaulia.aplikasikontak.db.DatabaseHelper
import com.rahmanaulia.aplikasikontak.model.Contact
import kotlinx.android.synthetic.main.activity_contact_add_update.*

class ContactAddUpdateActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_CONTACT = "extra_contact"
        const val EXTRA_POSITION = "extra_position"
        const val RESULT_CODE_ADD = 100
        const val RESULT_CODE_UPDATE = 101
        const val RESULT_CODE_DELETE = 102
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 11
    }

    private var isEdit = false
    private var contact: Contact? = null
    private var position: Int = 0
    private lateinit var contactHelper: ContactHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_add_update)

        initContactHelper()
        getDataContact()
        initView()
        initActionBar()
        onClick()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_delete -> deleteItem()
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteItem() {
        val result = contactHelper.deleteById(contact?.id.toString())
        if (result > 0){
            val intent = Intent()
            intent.putExtra(EXTRA_POSITION, position)
            setResult(RESULT_CODE_DELETE, intent)
            finish()
        }else{
            Toast.makeText(this, "Gagal menghapus", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClick() {
        btnSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            val phone = etPhone.text.toString().trim()

            if (name.isEmpty()){
                etName.error = "Tidak boleh kosong"
                return@setOnClickListener
            }

            if (phone.isEmpty()){
                etPhone.error = "Tidak boleh kosong"
                return@setOnClickListener
            }

            contact?.name = name
            contact?.phone = phone

            val intent = Intent()
            intent.putExtra(EXTRA_CONTACT, contact)
            intent.putExtra(EXTRA_POSITION, position)

            val values = ContentValues()
            values.put(DatabaseContract.ContactColumns.NAME, name)
            values.put(DatabaseContract.ContactColumns.PHONE, phone)

            if (isEdit){
                val result = contactHelper.update(contact?.id.toString(), values)
                if (result > 0){
                    setResult(RESULT_CODE_UPDATE, intent)
                    finish()
                }else{
                    Toast.makeText(this, "Gagal update", Toast.LENGTH_SHORT).show()
                }
            }else{
                val result = contactHelper.insert(values)
                if (result > 0){
                    contact?.id = result.toInt()
                    setResult(RESULT_CODE_ADD, intent)
                    finish()
                }else{
                    Toast.makeText(this, "Gagal submit", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isEdit){
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun initActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView() {
        val actionBarTitle: String
        val btnTitle: String

        if (isEdit){
            actionBarTitle = "Update"
            btnTitle = "Update"
            contact?.let {
                etName.setText(it.name)
                etPhone.setText(it.phone)
            }
        }else{
            actionBarTitle = "Submit"
            btnTitle = "Submit"
        }

        btnSubmit.text = btnTitle
        supportActionBar?.title = actionBarTitle
    }

    private fun getDataContact() {
        if (intent != null){
            contact = intent.getParcelableExtra(EXTRA_CONTACT)
            if (contact != null){
                position = intent.getIntExtra(EXTRA_POSITION, 0)
                isEdit = true
            }else{
                contact = Contact()
            }
        }
    }

    private fun initContactHelper() {
        contactHelper = ContactHelper.getInstance(this)
    }
}
