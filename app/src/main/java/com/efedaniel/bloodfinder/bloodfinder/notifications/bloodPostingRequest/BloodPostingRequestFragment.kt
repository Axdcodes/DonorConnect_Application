package com.efedaniel.bloodfinder.bloodfinder.notifications.bloodPostingRequest

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.efedaniel.bloodfinder.App

import com.efedaniel.bloodfinder.R
import com.efedaniel.bloodfinder.base.BaseFragment
import com.efedaniel.bloodfinder.base.BaseViewModel
import com.efedaniel.bloodfinder.bloodfinder.models.request.BloodPostingRequest
import com.efedaniel.bloodfinder.bloodfinder.models.request.UserDetails
import com.efedaniel.bloodfinder.databinding.FragmentBloodPostingRequestBinding
import com.efedaniel.bloodfinder.utils.ApiKeys
import com.efedaniel.bloodfinder.utils.Misc
import javax.inject.Inject

class BloodPostingRequestFragment : BaseFragment() {

    companion object {
        //todo
        const val BLOOD_POSTING_REQUEST_KEY = "AAAAAFnQMOY:APA91bFkeakkT7D04k-ELhLuUW9prE2FzlReHx31NHMxsj8U8Mf8dtgY90iDst_Ns09tpwBFG33UIch7_ku9HdXvN1o9JtWiZUD2Ua9CdII4Jt8hke6LDadjzinmJVmyrU7OLcp7r-dX"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentBloodPostingRequestBinding
    private lateinit var viewModel: BloodPostingRequestViewModel
    private lateinit var bloodPosting: BloodPostingRequest

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBloodPostingRequestBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(arguments!!.getParcelable<BloodPostingRequest>(BLOOD_POSTING_REQUEST_KEY) == null)
        (mainActivity.applicationContext as App).component.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BloodPostingRequestViewModel::class.java)
        binding.viewModel = viewModel

        bloodPosting = arguments!!.getParcelable(BLOOD_POSTING_REQUEST_KEY) ?: BloodPostingRequestFragmentArgs.fromBundle(arguments!!).bloodPosting
        viewModel.getBloodSeekerData(bloodPosting.bloodSeekerID)
        viewModel.bloodSeekerUserData.observe(viewLifecycleOwner, Observer { if (it != null) { bind(it) } })
        viewModel.notificationSentSuccessfully.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                showDialogWithAction(
                    body = getMessage(it),
                    positiveRes = R.string.close,
                    positiveAction = { mainActivity.onBackPressed() }
                )
            }
        })
    }

    private fun getMessage(status: String) = String.format(getString(R.string.blood_posting_response_message_format), status, bloodPosting.bloodSeekerFullName,
            if (bloodPosting.providerType == "Blood Donor") getString(R.string.blood_donor_posting_delete_message) else "")

    private fun bind(userDetails: UserDetails) {
        binding.bloodRequestMessageTextView.text = String.format(getString(R.string.blood_request_message_format),
            bloodPosting.bloodProviderFullName, bloodPosting.bloodSeekerFullName)
        binding.bloodSeekerNameTextView.text = bloodPosting.bloodSeekerFullName
        binding.bloodSeekerAddressTextView.text = userDetails.address
        binding.phoneNumberTextView.text = userDetails.phoneNumber
        binding.phoneNumberTextView.setOnClickListener { call(userDetails.phoneNumber!!) }
        binding.billingTypeTextView.text = String.format("%s Donation", bloodPosting.billingType)
        binding.acceptButton.setOnClickListener {
            showDialogWithAction(
                title = getString(R.string.accept_request),
                body = getString(R.string.accept_request_message),
                positiveRes = R.string.accept,
                negativeRes = R.string.cancel,
                positiveAction = { viewModel.updateBloodRequestStatus(bloodPosting, ApiKeys.ACCEPTED) }
            )
        }
        binding.declineButton.setOnClickListener {
            showDialogWithAction(
                title = getString(R.string.decline_request),
                body = getString(R.string.decline_request_message),
                positiveRes = R.string.decline,
                negativeRes = R.string.cancel,
                positiveAction = { viewModel.updateBloodRequestStatus(bloodPosting, ApiKeys.DECLINED) }
            )
        }
    }

    private fun call(phoneNumber: String) {
        val phoneIntent = Intent(Intent.ACTION_CALL)
        phoneIntent.data = Uri.parse("tel:$phoneNumber")
        if (ActivityCompat.checkSelfPermission(context!!, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(phoneIntent)
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), Misc.CALL_PERMISSION_REQUEST)
        }
    }

    private fun setUpToolbar(showUpIcon: Boolean) = mainActivity.run {
        setUpToolBar(getString(R.string.donation_request), showUpIcon)
        invalidateToolbarElevation(0)
    }

    override fun getViewModel(): BaseViewModel = viewModel
}
