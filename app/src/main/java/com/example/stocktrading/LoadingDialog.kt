package com.example.stocktrading

import android.app.AlertDialog

class LoadingDialog(val mActivity: LoginActivity) {
    private lateinit var isdialog: AlertDialog
    fun startLoading(){
        val infalter=mActivity.layoutInflater
        val dialogView=infalter.inflate(R.layout.loading_activity,null)

        val builder= AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog=builder.create()
        isdialog.show()
    }
    fun isDismiss(){
        isdialog.dismiss()
    }
}
class LoadingDialogs(val mActivity: registerActivity) {
    private lateinit var isdialog: AlertDialog
    fun startLoading(){
        val infalter=mActivity.layoutInflater
        val dialogView=infalter.inflate(R.layout.loading_activity,null)

        val builder= AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog=builder.create()
        isdialog.show()
    }
    fun isDismiss(){
        isdialog.dismiss()
    }
}
class changeEmailDialog(val mActivity: changeEmailActivity) {
    private lateinit var isdialog: AlertDialog
    fun startLoading(){
        val infalter=mActivity.layoutInflater
        val dialogView=infalter.inflate(R.layout.loading_activity,null)

        val builder= AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog=builder.create()
        isdialog.show()
    }
    fun isDismiss(){
        isdialog.dismiss()
    }
}
class deleteEmailDialog(val mActivity: deleteAccountActivity) {
    private lateinit var isdialog: AlertDialog
    fun startLoading(){
        val infalter=mActivity.layoutInflater
        val dialogView=infalter.inflate(R.layout.loading_activity,null)

        val builder= AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog=builder.create()
        isdialog.show()
    }
    fun isDismiss(){
        isdialog.dismiss()
    }
}
class confirmStocksDialog(val mActivity: ConfirmStocks) {
    private lateinit var isdialog: AlertDialog
    fun startLoading() {
        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.loading_activity, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog = builder.create()
        isdialog.show()
    }

    fun isDismiss() {
        isdialog.dismiss()
    }
}
//}
//class deleteStocksDialog(val mActivity: SellStocksActivity) {
//    private lateinit var isdialog: AlertDialog
//    fun startLoading(){
//        val infalter=mActivity.layoutInflater
//        val dialogView=infalter.inflate(R.layout.loading_activity,null)
//
//        val builder= AlertDialog.Builder(mActivity)
//        builder.setView(dialogView)
//        builder.setCancelable(false)
//        isdialog=builder.create()
//        isdialog.show()
//    }
//    fun isDismiss(){
//        isdialog.dismiss()
//    }
//}