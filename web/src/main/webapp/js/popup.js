function myFunct(myPopUp) {
    myPopUp.close();
    return false;
}

// Called from onload handler
function ModalPopupsAlert1() {
    ModalPopups.Alert("jsAlert1",
            "Bestellen von Papierabzügen / Ordering Prints",
            "<div style='padding:25px;'>Liebe Besucher<br/>" +
                    "Auf Ende Januar 2011 haben wir die Zusammenarbeit mit unserem langjährigen Partnerlabor \"Colormailer\" beendet.<br/>" +
                    "Wir bieten nach wie vor alle Fotos auf www.unartig.ch als Dateien an. <br/>" +
                    "Falls Sie Papierabzüge brauchen, laden Sie bitte die Dateien von www.unartig.ch herunter und senden Sie diese zu einem Fotolabor Ihrer Wahl. <br/>" +
                    "Vielen Dank.<br/>" +
                    "<br/>" +
                    "Dear Visitor<br/>" +
                    "We are no longer collaborating with our longstanding partner lab \"Colormailer\" since January 31, 2011<br/>" +
                    "We still are offering all photos on www.unartig.ch as digital files.<br/>" +
                    "If you are looking for paper prints, please download the files from our web site and send them to a lab of your choice.<br/>" +
                    "Thank you.<br/>" +

                    "</div>",
    {
        okButtonText: "Close"
    }
            );
}

var myimages = new Array();
function preloadimages() {
    for (i = 0; i < preloadimages.arguments.length; i++) {
        myimages[i] = new Image()
        myimages[i].src = preloadimages.arguments[i]
    }
}

// Pfad und Name der Images innerhalb von Anf�hrungszeichen eintragen.
// Liste nach Bedarf erweitern.
preloadimages(
        "/images/topMenu/link_home_active.gif",
        "/images/topMenu/link_home_spacer.gif",
        "/images/topMenu/link_about_active_de.gif",
        "/images/topMenu/link_about_active_en.gif",
        "/images/topMenu/link_about_active_fr.gif",
        "/images/topMenu/link_contact_active_de.gif",
        "/images/topMenu/link_contact_active_en.gif",
        "/images/topMenu/link_contact_active_fr.gif",
        "/images/topMenu/link_shopping_cart_active.gif",
        "/images/topMenu/link_login_active.gif",
        "/images/topMenu/link_logout_active.gif",
        "/images/topMenu/link_account_active_de.gif",
        "/images/topMenu/link_account_active_en.gif",
        "/images/topMenu/link_account_active_fr.gif",
        "/images/topMenu/link_question_active.gif",
        "/images/tree_icons/locker.gif",
        "/images/tree_icons/locker_hover.gif",
        "/images/tree_icons/locker_selected_hover.gif",
        "/images/tree_icons/spacer_icon_e.gif",
        "/images/tree_icons/spacer_icon_l.gif",
        "/images/tree_icons/minus.gif",
        "/images/tree_icons/plus.gif",
        "/images/tree_icons/spacer_icon_folder.gif",
        "/images/tree_icons/fest_anlaesse_closed_de.gif",
        "/images/tree_icons/fest_anlaesse_closed_en.gif",
        "/images/tree_icons/fest_anlaesse_closed_fr.gif",
        "/images/tree_icons/fest_anlaesse_open_de.gif",
        "/images/tree_icons/fest_anlaesse_open_en.gif",
        "/images/tree_icons/fest_anlaesse_open_fr.gif",
        "/images/tree_icons/fest_anlaesse_hover_de.gif",
        "/images/tree_icons/fest_anlaesse_hover_en.gif",
        "/images/tree_icons/fest_anlaesse_hover_fr.gif",
        "/images/tree_icons/sport_anlaesse_closed_de.gif",
        "/images/tree_icons/sport_anlaesse_closed_en.gif",
        "/images/tree_icons/sport_anlaesse_closed_fr.gif",
        "/images/tree_icons/sport_anlaesse_open_de.gif",
        "/images/tree_icons/sport_anlaesse_open_en.gif",
        "/images/tree_icons/sport_anlaesse_open_fr.gif",
        "/images/tree_icons/sport_anlaesse_hover_de.gif",
        "/images/tree_icons/sport_anlaesse_hover_en.gif",
        "/images/tree_icons/sport_anlaesse_hover_fr.gif"
        );