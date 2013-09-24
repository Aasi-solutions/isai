<?php
/*
Plugin Name: Malayalam Writer
Plugin URI: http://www.monusoft.com/Products/WordPressPlugin/tabid/187/Default.aspx
Description: This plugin lets the user write Malayalam directly in post/page/comment box using transliterate Malayalam keyboard layout.
Author: Monusoft
Version: 1.1
Author URI: http://www.monusoft.com/
Change Log:
2006-11-05	Adding type pad to post and page
2006-11-04  Changing the way to comment.
*/
if (!class_exists('MalayalamKeyPad')) {
class MalayalamKeyPad
{
    function MalayalamKeyPad()
    {
        add_action('comment_form', array(&$this, 'comment_form'));	//add Malayalam Type Pad to comment form
        add_action('edit_form_advanced', array(&$this, 'post_form'));	//add Malayalam Type Pad to Advanced Post
        add_action('simple_edit_form', array(&$this, 'post_form'));	//add Malayalam Type Pad to Simple Post
        add_action('edit_page_form', array(&$this, 'post_form'));	//add Malayalam Type Pad to Page
    }
	
    function render($parentElement,$txtareaName)
    {

        ?>
        <script language=javascript src="<?php echo get_settings('siteurl');?>/wp-content/plugins/MalayalamWriter/Parser.js"></script>
        <script language=javascript src="<?php echo get_settings('siteurl');?>/wp-content/plugins/MalayalamWriter/Malayalam.js"></script>
        <script language=javascript type="text/javascript">
        function toggleLayer(whichLayer,m)
		{
			var hide = 'Hide Keyboard';
			var show = 'Show Keyboard';
			if (document.getElementById)
			{
				var style2 = document.getElementById(whichLayer).style;
				style2.display = style2.display!='none'? 'none':'';
				document.getElementById(m).firstChild.data = style2.display!='none' ? hide:show;
			}
			else if (document.all)
			{
				var style2 = document.all[whichLayer].style;
				style2.display = style2.display!='none'? 'none':'';
				document.all[m].innerHTML = style2.display!='none' ?  hide:show;
			}
			else if (document.layers)
			{
				var style2 = document.layers[whichLayer].style;
				style2.display = style2.display!='none'? 'none':'';
				document.layers[m].innerHTML = style2.display!='none' ?  hide:show;
			}
		}
        </script>
		<div style="display: left;align:left;" id="Malayalam">
            <p style="align:left;">
            <label for="lang"><small>Select language:</small></label><input type="radio" value="Malayalam" name="language" id="language1" style="width:auto;" onclick="isEng= false;" checked><label for="Malayalam"><small>Malayalam</small></label>&nbsp;<input type="radio" name="language" id="language2" style="width:auto;" value="English" onclick="isEng= true;"><label for="English"><small>English</small></label>
			<label for="ChangeLanguage"><small>(Press F12 to change language)</small></label>
         	<small class="commentmetadata"><a href="javascript:toggleLayer('MalayalamCharacterMap','i');" id="i">Show Keyboard</a></small>
            <div id="MalayalamCharacterMap" style="display:none;"><img src="<?php echo get_settings('siteurl');?>/wp-content/plugins/MalayalamWriter/MalayalamMap.JPG" alt="Malayalam Character Map"/></div>
            </p>
        </div>
        <script language="JavaScript" type="text/javascript">
			var html, urlp;
            var com = document.getElementById("<?php echo $txtareaName; ?>");
			var mozilla = document.getElementById&&!document.all;
			if (mozilla)
	            html = com.parentNode.innerHTML;
			else
	            html = com.parentElement.innerHTML;
            var newcomment = new RegExp(html);
            var str = html.replace(/<(\s*)textarea (.*?)>(.*)/i,"<textarea $2 onkeypress=\"if (!isEng){ return change(this,event);} else {return true;}\" onFocus=\"changeCursor(this);\" onClick=\"changeCursor(this);\" onKeyUp=\"changeCursor(this);\" onkeydown=\"positionChange(event);\">$3");
			if (mozilla)
	            com.parentNode.innerHTML = str;
			else
	            com.parentElement.innerHTML = str;
            var url = document.getElementById("<?php echo $parentElement; ?>");
			if (mozilla)
	            urlp = url.parentNode;
			else
				    urlp = url.parentElement;
            var sub = document.getElementById("Malayalam");
            urlp.appendChild(sub, url);
        </script>
        <?php
    }
	function comment_form()
	{
        global $user_ID;
		if (isset($user_ID)) 
			$this->render("comment","comment");
		else
			$this->render("url","comment");
	}
	function post_form()
	{
		$this->render("quicktags","content");
	}
}	//end class
}   //end if
$typepad = new MalayalamKeyPad();
?>