function myFunct(myPopUp)
{
    myPopUp.close();
    return false;
}

var myimages = new Array();
function preloadimages()
{
    for (i = 0; i < preloadimages.arguments.length; i++)
    {
        myimages[i] = new Image()
        myimages[i].src = preloadimages.arguments[i]
    }
}

// Pfad und Name der Images innerhalb von Anführungszeichen eintragen.
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