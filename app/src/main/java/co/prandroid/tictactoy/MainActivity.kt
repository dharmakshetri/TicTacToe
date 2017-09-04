package co.prandroid.tictactoy


import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import co.prandroid.tictactoy.Utils.Companion.gameStrategy3
import co.prandroid.tictactoy.Utils.Companion.gameStrategy4
import co.prandroid.tictactoy.Utils.Companion.playerOne
import co.prandroid.tictactoy.Utils.Companion.playerTwo
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {


    var activePlayer=1


    var mpTap: MediaPlayer? = null
    var mpWinner: MediaPlayer? = null
    var strType:String? = null


    var  pointYou: Int = 0
    var  pointMobile: Int = 0
    var  pointYouF: Int = 0
    var  pointFriend: Int = 0

    var guestName:String?=""

    var isPlaying: Boolean = false

    var noOfCards: Int = 0

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
        noOfCards = sharedPref.getInt("gs", 3)

        println(" when geting data: noOfCards $noOfCards")
        println(" when geting data: you -> $pointYou   Mobile-> $pointMobile   Type: $strType")
        println(" when geting data: you -> $pointYouF   $guestName-> $pointFriend   Type: $strType")

        linearPoints.visibility=View.VISIBLE
        setPointOnView(strType)
        setUpNoOfCardsView()
        playerOne.clear()
        playerTwo.clear()
    }

    private fun setUpNoOfCardsView() {
        setViews()
    }

    fun tvGuestClick(view: View){
        inputNameDialog()
    }

    fun tvNoOfTypeClick(view: View) {
        println("tvNoOfTypeClick=$noOfCards")
        when (noOfCards) {
            3 -> {
                setViews()
                // playerOne.clear()
                playerTwo.clear()
                noOfCards = 4
            }
            4 -> {
                setViews()
                playerOne.clear()
                // playerTwo.clear()
                noOfCards = 3
            }
        }
    }

    fun setViews() {
        println("noOfCards=$noOfCards and no=$noOfCards")
        if (noOfCards == 3) {
            println("3 set tables =$noOfCards")
            tableLayout3.visibility = View.VISIBLE
            tableLayout4.visibility = View.GONE
            tvNoOfTtpes.text = "4 x 4"
            noOfCards = 3
        } else if (noOfCards == 4) {
            println("4 set tables =$noOfCards")
            tableLayout4.visibility = View.VISIBLE
            tableLayout3.visibility = View.GONE
            tvNoOfTtpes.text = "3 x 3"
            noOfCards = 4
        }
        println("No Of cards=$noOfCards")
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
            textViewGuest.isClickable = false
            imageViewMobile.setImageResource(R.drawable.mobileon)
            imageViewFriends.setImageResource(R.drawable.friend)
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

            textViewGuest.isClickable = true
            imageViewMobile.setImageResource(R.drawable.mobile)
            imageViewFriends.setImageResource(R.drawable.friendon)
        }
    }

    fun btnClick(view: View){
        val btnSelected= view as Button
        var cellId=0
        var textFormat = tvNoOfTtpes.text
        println("textFormat $textFormat and noOfCards=$noOfCards")
        if (textFormat.equals("3 x 3")) {
            noOfCards = 4
            println("textFornoOfCards=$noOfCards")
        } else {
            noOfCards = 3
        }
        println("no btn click= $noOfCards")
        when (noOfCards) {
            3 -> {
                when (btnSelected.id) {
                    R.id.btn1 -> cellId = 1
                    R.id.btn2 -> cellId = 2
                    R.id.btn3 -> cellId = 3
                    R.id.btn4 -> cellId = 4
                    R.id.btn5 -> cellId = 5
                    R.id.btn6 -> cellId = 6
                    R.id.btn7 -> cellId = 7
                    R.id.btn8 -> cellId = 8
                    R.id.btn9 -> cellId = 9
                    else -> {
                        Toast.makeText(this, " Please select Proper Cell in 3 x 3", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            4 -> {
                when (btnSelected.id) {
                    R.id.btn11 -> cellId = 1
                    R.id.btn12 -> cellId = 2
                    R.id.btn13 -> cellId = 3
                    R.id.btn14 -> cellId = 4
                    R.id.btn15 -> cellId = 5
                    R.id.btn16 -> cellId = 6
                    R.id.btn17 -> cellId = 7
                    R.id.btn18 -> cellId = 8
                    R.id.btn19 -> cellId = 9
                    R.id.btn20 -> cellId = 10
                    R.id.btn21 -> cellId = 11
                    R.id.btn22 -> cellId = 12
                    R.id.btn23 -> cellId = 13
                    R.id.btn24 -> cellId = 14
                    R.id.btn25 -> cellId = 15
                    R.id.btn26 -> cellId = 16
                    else -> {
                        Toast.makeText(this, " Please select Proper Cell in 4 x 4", Toast.LENGTH_SHORT).show()
                    }
                }
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
            println("winnerFound:$winnerFound")
            var total = playerOne.size + playerTwo.size
            println("Total ListSize:$total")
            when (noOfCards) {
                3 -> {
                    if (winnerFound)
                        return
                    if ((playerOne.size + playerTwo.size < 9)) {
                        playingType(strType)
                    } else {
                        winnerDialog("One", "Game Draw, would you like to play again!!!");
                    }
                }
                4 -> {
                    if (winnerFound)
                        return
                    if ((playerOne.size + playerTwo.size < 16)) {
                        playingType(strType)
                    } else {
                        winnerDialog("One", "Game Draw, would you like to play again!!!");
                    }
                }
            }


        }else{
            btnSelected.text="0"
            btnSelected.setBackgroundResource(R.color.md_green_A700)
            playerTwo.add(cellId)
            activePlayer=1
            var winnerFound = findWinner(strType)
            println("winnerFound:$winnerFound")
            var total = playerOne.size + playerTwo.size
            println("Total ListSize:$total")
            when (noOfCards) {
                3 -> {
                    if (winnerFound)
                        return
                    if ((playerOne.size + playerTwo.size == 9)) {
                        winnerDialog("One", "Game Draw, would you like to play again!!!");
                    }
                }
                4 -> {
                    if (winnerFound)
                        return
                    if ((playerOne.size + playerTwo.size == 16)) {
                        winnerDialog("One", "Game Draw, would you like to play again!!!");
                    }
                }
            }
        }

        btnSelected.isEnabled=false
        isPlaying = true
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
        println("findWinner noOfCards $noOfCards ")
        when (noOfCards) {
            3 -> {
                winner = gameStrategy3()
                println("gameStrategy3: ${gameStrategy3()}")
            }
            4 -> {
                winner = gameStrategy4()
                println("gameStrategy4(): ${gameStrategy4()}")
            }
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

        playerTwo.clear()
        playerOne.clear()
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
                println(" when saving data: noOfCards $noOfCards")
                editor.putInt(getString(R.string.pointyou), pointYou)
                editor.putInt(getString(R.string.pointmobile), pointMobile)
                editor.putInt(getString(R.string.pointyouf), pointYouF)
                editor.putInt(getString(R.string.pointfriend), pointFriend)
                editor.putInt("gs", noOfCards)
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
            when (noOfCards) {
                3 -> {
                    for (cellID in 1..9) {
                        if (!(playerOne.contains(cellID) || playerTwo.contains(cellID))) {
                            emptyCell.add(cellID)
                        }
                    }
                }

                4 -> {
                    for (cellID in 1..16) {
                        if (!(playerOne.contains(cellID) || playerTwo.contains(cellID))) {
                            emptyCell.add(cellID)
                        }
                    }
                }
            }


            var random= Random()
            var randomIndex:Int?
            randomIndex=random.nextInt(emptyCell.size-0)+0
            println("randomIndex $randomIndex")
            val emptyCellId =emptyCell[randomIndex]
            println("emptyCellId $emptyCellId")
            var btnSelect: Button?
            btnSelect = setButtonId(noOfCards, emptyCellId)

            playGame(emptyCellId, btnSelect)
        } catch (ex: Exception) {
            println(ex.message)
        }

    }

    private fun setButtonId(noOfCards: Int, emptyCellId: Int): Button {
        when {
            (noOfCards == 3 && emptyCellId == 1) -> return btn1
            (noOfCards == 3 && emptyCellId == 2) -> return btn2
            (noOfCards == 3 && emptyCellId == 3) -> return btn3
            (noOfCards == 3 && emptyCellId == 4) -> return btn4
            (noOfCards == 3 && emptyCellId == 5) -> return btn5
            (noOfCards == 3 && emptyCellId == 6) -> return btn6
            (noOfCards == 3 && emptyCellId == 7) -> return btn7
            (noOfCards == 3 && emptyCellId == 8) -> return btn8
            (noOfCards == 3 && emptyCellId == 9) -> return btn9
            (noOfCards == 4 && emptyCellId == 1) -> return btn11
            (noOfCards == 4 && emptyCellId == 2) -> return btn12
            (noOfCards == 4 && emptyCellId == 3) -> return btn13
            (noOfCards == 4 && emptyCellId == 4) -> return btn14
            (noOfCards == 4 && emptyCellId == 5) -> return btn15
            (noOfCards == 4 && emptyCellId == 6) -> return btn16
            (noOfCards == 4 && emptyCellId == 7) -> return btn17
            (noOfCards == 4 && emptyCellId == 8) -> return btn18
            (noOfCards == 4 && emptyCellId == 9) -> return btn19
            (noOfCards == 4 && emptyCellId == 10) -> return btn20
            (noOfCards == 4 && emptyCellId == 11) -> return btn21
            (noOfCards == 4 && emptyCellId == 12) -> return btn22
            (noOfCards == 4 && emptyCellId == 13) -> return btn23
            (noOfCards == 4 && emptyCellId == 14) -> return btn24
            (noOfCards == 4 && emptyCellId == 15) -> return btn25
            (noOfCards == 4 && emptyCellId == 16) -> return btn26
        }
        return Button(applicationContext)
    }


    //
    fun imgMobileClick(view: View){
        strType="mobile"
        setPointOnView(strType)

        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(getString(R.string.type), strType)
        editor.commit()
        if (isPlaying) {
            val intent = intent
            finish()
            startActivity(intent)
        }
        playerOne.clear()
        playerTwo.clear()

    }

    //
    fun imgFriendsClick(view: View){
        strType="friend"
        setPointOnView(strType)


        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(getString(R.string.type), strType)
        editor.commit()

        if (isPlaying) {
            val intent = intent
            finish()
            startActivity(intent)
        }
        playerOne.clear()
        playerTwo.clear()
    }

    //
    fun imgBluetoothClick(view: View){
       Toast.makeText(this, "Bluetooh player is aviable right now", Toast.LENGTH_SHORT).show()
        tvType.text="Bluetooth service not provided yet."
    }


}
