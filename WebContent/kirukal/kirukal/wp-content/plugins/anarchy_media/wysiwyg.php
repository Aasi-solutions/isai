<?php
/*********************************************************************
* Optional WYSIWYG extension to the tinyMCE editor (turn on/off in config.php if it conflicts with other tinyMCE customizations)
**********************************************************************/
if ($wysiwyg == 'true') {
	if (isset($wp_version)) {
	//add_filter("mce_plugins", "anarchy_extended_mce_plugins", 0);
    add_filter("mce_buttons", "anarchy_extended_mce_buttons", 0);
    add_filter("mce_buttons_2", "anarchy_extended_mce_buttons_2", 0);
    //add_filter("mce_buttons_3", "anarchy_extended_mce_buttons_3", 0);
    }
}

//Uncomment this function and the plugins call above to add uploaded plugins
/*
function anarchy_extended_mce_plugins($plugins) {
    array_push($plugins, "table", "fullscreen", "emoticons");
    return $plugins;
}
*/
function anarchy_extended_mce_buttons($buttons) {
global $wp_db_version;  
	if ( 3664 <= $wp_db_version) {  
	return array(
        "bold", "italic", "underline", "strikethrough", "separator", "link", "unlink", "anchor", "image", "wp_more", "separator",
        "sub", "sup", "forecolor", "backcolor", "charmap");
    } else {
	return array(			
			"bold", "italic", "underline", "strikethrough", "separator", "link", "unlink", "anchor", 
			"image", "wordpress", "separator", "sub", "sup", "forecolor", "backcolor", "charmap"); 
	}
        
}
function anarchy_extended_mce_buttons_2($buttons) {
    // Add buttons on the second toolbar line
global $wp_db_version;  
	if ( 3664 <= $wp_db_version) {  
    return array(
         "formatselect", "bullist", "numlist", "indent", "outdent", 
        "separator", "justifyleft", "justifycenter", "justifyright", 
        "justifyfull", "separator", "undo", "redo", 
        "separator", "spellchecker", "separator", "wordpress", "wp_help");
    } else {
		return array(
			"formatselect","bullist", "numlist", "indent", "outdent", "separator","justifyleft", 
			"justifycenter", "justifyright", "justifyfull", "separator", 
			"undo", "redo", "separator", "code", "separator", "wphelp");
	}
    
    return $buttons;
}
/*
function anarchy_extended_mce_buttons_3($buttons) {
    // Add buttons on the second toolbar line
    return array(
         "tablecontrols");
    return $buttons;
}
*/
?>