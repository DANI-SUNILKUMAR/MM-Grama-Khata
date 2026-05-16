package com.gramakhata.app.ui

<<<<<<< HEAD
=======
import android.content.Context
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
<<<<<<< HEAD
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gramakhata.app.data.prefs.SessionManager
import com.gramakhata.app.databinding.ActivityMainBinding
import com.gramakhata.app.ui.customers.AddCustomerActivity
import com.gramakhata.app.ui.customers.CustomerDetailActivity
import com.gramakhata.app.ui.customers.CustomerAdapter
import com.gramakhata.app.viewmodel.KhataViewModel
import java.text.NumberFormat
import java.util.Locale
=======
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gramakhata.app.R
import com.gramakhata.app.databinding.ActivityMainBinding
import com.gramakhata.app.ui.customers.AddCustomerActivity
import com.gramakhata.app.ui.customers.CustomerAdapter
import com.gramakhata.app.ui.customers.CustomerDetailActivity
import com.gramakhata.app.viewmodel.KhataViewModel
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: KhataViewModel by viewModels()
    private lateinit var customerAdapter: CustomerAdapter
<<<<<<< HEAD
    private lateinit var sessionManager: SessionManager
=======
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

<<<<<<< HEAD
        sessionManager = SessionManager(this)

        if (!sessionManager.isLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        setupUI()
=======
        // Show shop name in header if available
        val prefs = getSharedPreferences("gramakhata_prefs", Context.MODE_PRIVATE)
        val shopName = prefs.getString("shop_name", "Due Dashboard") ?: "Due Dashboard"
        binding.tvShopName.text = shopName

        setSupportActionBar(binding.toolbar)
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
        setupRecyclerView()
        setupObservers()
        setupListeners()
    }

<<<<<<< HEAD
    private fun setupUI() {
        binding.tvUserRole.text = "Dashboard - ${sessionManager.getUserRole()}"
        if (sessionManager.isAdmin()) {
            binding.fabAddCustomer.show()
        } else {
            binding.fabAddCustomer.hide()
=======
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Logout") { _, _ ->
                        val prefs = getSharedPreferences("gramakhata_prefs", Context.MODE_PRIVATE)
                        prefs.edit().putBoolean("is_logged_in", false).apply()
                        startActivity(Intent(this, LoginActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        })
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
        }
    }

    private fun setupRecyclerView() {
        customerAdapter = CustomerAdapter { customer ->
            val intent = Intent(this, CustomerDetailActivity::class.java)
            intent.putExtra("customer_id", customer.id)
            startActivity(intent)
        }
        binding.rvCustomers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = customerAdapter
        }
    }

    private fun setupObservers() {
        viewModel.displayedCustomers.observe(this) { customers ->
            customerAdapter.submitList(customers)
            binding.tvEmptyState.visibility = if (customers.isEmpty()) View.VISIBLE else View.GONE
            binding.rvCustomers.visibility = if (customers.isEmpty()) View.GONE else View.VISIBLE
        }

        viewModel.totalDues.observe(this) { total ->
<<<<<<< HEAD
            val amount = total ?: 0.0
            binding.tvTotalDue.text = formatCurrency(amount)
=======
            binding.tvTotalDue.text = "₹${String.format("%,.2f", total ?: 0.0)}"
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
        }

        viewModel.activeDebtorCount.observe(this) { count ->
            binding.tvDebtorCount.text = "$count customers"
        }
    }

    private fun setupListeners() {
        binding.fabAddCustomer.setOnClickListener {
            startActivity(Intent(this, AddCustomerActivity::class.java))
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setSearchQuery(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

<<<<<<< HEAD
        binding.btnShareReport.setOnClickListener {
            shareReport()
        }

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        sessionManager.logout()
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
=======
        binding.btnShareReport.setOnClickListener { shareReport() }
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
    }

    private fun shareReport() {
        val customers = viewModel.displayedCustomers.value ?: return
        val total = viewModel.totalDues.value ?: 0.0
<<<<<<< HEAD
        val sb = StringBuilder()
        sb.appendLine("📒 *Grama-Khata Daily Report*")
        sb.appendLine("━━━━━━━━━━━━━━━━━━")
        sb.appendLine()
=======
        val prefs = getSharedPreferences("gramakhata_prefs", Context.MODE_PRIVATE)
        val shopName = prefs.getString("shop_name", "My Shop") ?: "My Shop"
        val sb = StringBuilder()
        sb.appendLine("📒 *$shopName — Daily Report*")
        sb.appendLine("━━━━━━━━━━━━━━━━━━")
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
        customers.filter { it.netDue > 0 }.forEach { c ->
            sb.appendLine("👤 ${c.name}  →  ₹${String.format("%.2f", c.netDue)}")
        }
        sb.appendLine()
        sb.appendLine("━━━━━━━━━━━━━━━━━━")
        sb.appendLine("💰 *Total Due: ₹${String.format("%.2f", total)}*")
<<<<<<< HEAD
        sb.appendLine()
        sb.appendLine("_Sent from Grama-Khata App_")

=======
        sb.appendLine("_Sent from Grama-Khata App_")
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, sb.toString())
        }
        startActivity(Intent.createChooser(intent, "Share Report via"))
    }
<<<<<<< HEAD

    private fun formatCurrency(amount: Double): String {
        return "₹${String.format("%,.2f", amount)}"
    }
=======
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
}
