package co.prandroid.tictactoe


import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE
import android.view.Window
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    var playerOne= ArrayList<Int>()
    var playerTwo= ArrayList<Int>()
    var activePlayer=1


    var mpTap: MediaPlayer? = null
    var mpWinner: MediaPlayer? = null
    var strType:String? = null


    var  pointYou: Int = 0
    var  pointMobile: Int = 0
    var  pointYouF: Int = 0
    var  pointFriend: Int = 0

    var guestName:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvType.text=resources.getString(R.string.app_name)

        mpTap = MediaPlayer.create(this, R.raw.pool)
        mpWinner = MediaPlayer.create(this, R.raw.winner)

        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        pointYou = sharedPref.getInt(getString(R.string.pointyou), 0)
        pointMobile = sharedPref.getInt(getString(R.string.pointmobile), 0)
        pointYouF = sharedPref.getInt(getString(R.string.pointyouf), 0)
        pointFriend = sharedPref.getInt(getString(R.string.pointfriend), 0)
        guestName=sharedPref.getString(getString(R.string.guestName)," ")
        strType=sharedPref.getString(getString(R.string.type),"mobile")

        println(" when geting data: you -> $pointYou   Mobile-> $pointMobile   Type: $strType")
        println(" when geting data: you -> $pointYouF   $guestName-> $pointFriend   Type: $strType")


        //tvType.text=" you vs $strType"

        linearPoints.visibility=View.VISIBLE
        setPointOnView(strType)

    }

    fun tvGuestClick(view: View){
        inputNameDialog()
    }

    // display point on view
    fun setPointOnView(type:String?){
        println("TYPE: $type")
        if(type == "mobile"){
            textViewPoint1.text=" You: $pointYou"
            textViewPoint2.text=" Mobile: $pointMobile"
            topLinear.visibility=View.VISIBLE
            textViewYou.text="You"
            textViewGuest.text="Mobile"
        }
        if(type=="friend"){
            println("Guest name is: $guestName")
            if(guestName.isNullOrBlank()){
                guestName="friend"
            }
            textViewPoint1.text=" You: $pointYouF"
            textViewPoint2.text=" $guestName: $pointFriend"
            topLinear.visibility=View.VISIBLE
            textViewYou.text="You"
            textViewGuest.text=guestName
        }
    }

    fun btnClick(view: View){
        val btnSelected= view as Button
        var cellId=0

        when(btnSelected.id){
            R.id.btn1 ->cellId =1
            R.id.btn2 ->cellId =2
            R.id.btn3 ->cellId =3
            R.id.btn4 ->cellId =4
            R.id.btn5 ->cellId =5
            R.id.btn6 ->cellId =6
            R.id.btn7 ->cellId =7
            R.id.btn8 ->cellId =8
            R.id.btn9 ->cellId =9
            else->{
                Toast.makeText(this, " Please select Proper Cell", Toast.LENGTH_SHORT).show()
            }
        }

        playGame(cellId,btnSelected)
        linearPoints.visibility=View.VISIBLE
    }

    fun playGame(cellId: Int, btnSelected: Button){

        mpTap?.start()


        if(activePlayer==1){
            btnSelected.text="X"
            btnSelected.setBackgroundResource(R.color.md_blue_A700)
            playerOne.add(cellId)
            activePlayer=2
            println("total selecteed: ${playerOne.size+playerTwo.size}")

            var winnerFound = findWinner(strType)

            if(winnerFound)
                return
            if((playerOne.size+playerTwo.size<9)){
                playingType(strType)

            }else{
                winnerDialog("One","Game Draw, would you like to play again!!!");
            }

        }else{
            btnSelected.text="0"
            btnSelected.setBackgroundResource(R.color.md_green_A700)
            playerTwo.add(cellId)
            activePlayer=1
            findWinner(strType)
        }

        btnSelected.isEnabled=false

    }

    // playing category
    fun playingType( type:String?){
        when(type){
            "mobile" -> autoPlay()
            "friend" -> friendPlay()
            "bluetooth" -> bluetoothPlay()
        }
    }

    // playwith friend
    fun friendPlay(){

    }

    // playing with bluetooth
    fun bluetoothPlay(){

    }

    // find the who is winner and winning formula
    fun findWinner(str:String?):Boolean{
        var winner=-1


        // ********* ROW *************
        // row1
        if(playerOne.contains(1) && playerOne.contains(2) && playerOne.contains(3)){
            winner=1
        }
        // row1
        if(playerTwo.contains(1) && playerTwo.contains(2) && playerTwo.contains(3)){
            winner=2
        }

        // row2
        if(playerOne.contains(4) && playerOne.contains(5) && playerOne.contains(6)){
            winner=1
        }
        // row1
        if(playerTwo.contains(4) && playerTwo.contains(5) && playerTwo.contains(6)){
            winner=2
        }

        // row1
        if(playerOne.contains(7) && playerOne.contains(8) && playerOne.contains(9)){
            winner=1
        }
        // row3
        if(playerTwo.contains(7) && playerTwo.contains(8) && playerTwo.contains(9)){
            winner=2
        }



        // ********* COLUMN *************


        // col 1
        if(playerOne.contains(1) && playerOne.contains(4) && playerOne.contains(7)){
            winner=1
        }
        // col 1
        if(playerTwo.contains(1) && playerTwo.contains(4) && playerTwo.contains(7)){
            winner=2
        }

        // col 2
        if(playerOne.contains(2) && playerOne.contains(5) && playerOne.contains(8)){
            winner=1
        }
        // col 2
        if(playerTwo.contains(2) && playerTwo.contains(5) && playerTwo.contains(8)){
            winner=2
        }

        // col 3
        if(playerOne.contains(3) && playerOne.contains(6) && playerOne.contains(9)){
            winner=1
        }
        // col 3
        if(playerTwo.contains(3) && playerTwo.contains(6) && playerTwo.contains(9)){
            winner=2
        }


        //***** DIAGONAL **********

        // diagonal 1
        if(playerOne.contains(1) && playerOne.contains(5) && playerOne.contains(9)){
            winner=1
        }
        // diagonal 1
        if(playerTwo.contains(1) && playerTwo.contains(5) && playerTwo.contains(9)){
            winner=2
        }

        // diagonal 2
        if(playerOne.contains(3) && playerOne.contains(5) && playerOne.contains(7)){
            winner=1
        }
        // diagonal 1
        if(playerTwo.contains(3) && playerTwo.contains(5) && playerTwo.contains(7)){
            winner=2
        }
        // Winner startegy
        println(" Winner Value = $winner")
        println(" Player one values "+ playerOne.toString())
        println(" Player tow values "+ playerTwo.toString())
        if(winner != -1){
            if(winner == 1){
                winnerDialog("one","Winner: YOU, would you like to play again!!!");
                return true
            }else{
                if(str.equals("friend"))  winnerDialog("two","Winner: ${guestName},\n would you like to play again!!!") else winnerDialog("two","Winner: ${str?.toUpperCase()},\n would you like to play again!!!")
                return true
            }
        }
        return false
    }


    // input guest player name
    fun inputNameDialog(){
        val alert=AlertDialog.Builder(this)
        with(alert){
            setTitle(getString(R.string.enteryouname))
            var editText=EditText(this@MainActivity)
            alert.setView(editText)
            getWindow()!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
            setPositiveButton(getString(R.string.ok)){
                dialog, which ->
                val input=editText.text.toString()
                println(" Input Value: $input")
                textViewGuest.text=input
                textViewPoint2.text=" $input: $pointFriend"
                val sharedPref = getPreferences(Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString(getString(R.string.guestName),input.toString())
                editor.commit()
            }

            setNegativeButton(getString(R.string.no)){
                dialog, which ->
                dialog.dismiss()
            }
        }
        var dialog=alert.create()
        dialog.show()
    }

    // display dialog box if the game draw and either winning
    fun winnerDialog(name:String, message:String){

        mpWinner?.start()

        val alert = AlertDialog.Builder(this)
        // Builder
        with (alert) {
            setTitle(getString(R.string.hurry))
            setMessage("$message")
            // Add input field

            setPositiveButton(getString(R.string.ok)) {
                dialog, whichButton ->

                val sharedPref = getPreferences(Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString(getString(R.string.type), strType)

                if(strType.equals("mobile")){
                    if (name.equals("one")) {
                        pointYou += 1
                     } else if (name.equals("two")) {
                     pointMobile+=1
                     }
                }

                if(strType.equals("friend")){
                    if (name.equals("one")) {
                        pointYouF += 1
                    } else if (name.equals("two")) {
                        pointFriend+=1
                    }
                }
                println(" when saving data: you -> $pointYou   Mobile-> $pointMobile  Type: $strType")
                println(" when saving data: you -> $pointYouF   Friends-> $pointFriend  Type: $strType")
                editor.putInt(getString(R.string.pointyou), pointYou)
                editor.putInt(getString(R.string.pointmobile), pointMobile)
                editor.putInt(getString(R.string.pointyouf), pointYouF)
                editor.putInt(getString(R.string.pointfriend), pointFriend)
                editor.commit()

                dialog.dismiss()
                val intent = intent
                finish()
                startActivity(intent)
            }
            setNegativeButton(getString(R.string.no)) { dialog, whichButton ->
                //showMessage("Close the game !")
                finish()
                dialog.dismiss()
            }

        }

        // Dialog
        val dialog = alert.create()
        dialog.show()
    }

    // Mobile Turn or auto play with mobile
    fun autoPlay(){
        try {
            //if(w)
            var emptyCell= ArrayList<Int>()
            for(cellID in 1..9){
                if(!(playerOne.contains(cellID) || playerTwo.contains(cellID))){
                    emptyCell.add(cellID)
                }
            }

            var random= Random()
            var randomIndex:Int?
            randomIndex=random.nextInt(emptyCell.size-0)+0
            println("randomIndex $randomIndex")
            val emptyCellId =emptyCell[randomIndex]
            println("emptyCellId $emptyCellId")
            var btnSelect: Button?
            when(emptyCellId){
                1 -> btnSelect=btn1
                2 -> btnSelect=btn2
                3 -> btnSelect=btn3
                4 -> btnSelect=btn4
                5 -> btnSelect=btn5
                6 -> btnSelect=btn6
                7 -> btnSelect=btn7
                8 -> btnSelect=btn8
                9 -> btnSelect=btn9
                else -> btnSelect=btn1
            }

            playGame(emptyCellId, btnSelect)
        } catch (ex:Exception){
            println(ex.message)
        }

    }


    //
    fun imgMobileClick(view: View){
        strType="mobile"
        setPointOnView(strType)
        textViewGuest.isClickable=false
        imageViewMobile.setImageResource(R.drawable.mobileon)
        imageViewFriends.setImageResource(R.drawable.friend)
    }

    //
    fun imgFriendsClick(view: View){
        strType="friend"
        setPointOnView(strType)
        textViewGuest.isClickable=true
        imageViewMobile.setImageResource(R.drawable.mobile)
        imageViewFriends.setImageResource(R.drawable.friendon)
    }

    //
    fun imgBluetoothClick(view: View){
       Toast.makeText(this, "Bluetooh player is aviable right now", Toast.LENGTH_SHORT).show()
        tvType.text="Bluetooth service not provided yet."
    }


}
