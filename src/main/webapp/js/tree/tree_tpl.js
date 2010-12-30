/*
	Feel free to use your custom icons for the tree. Make sure they are all of the same size.
	If you don't use some keys you can just remove them from this config
*/

var TREE_TPL = {

// general
    'target':'_self',    // name of the frame links will be opened in
// other possible values are:
// _blank, _parent, _search, _self and _top
    'cookie_ext':'path=/;',



// icons - node
    'icon_16':'/images/tree_icons/spacer_icon_folder.gif', // node icon normal
    'icon_20':'/images/tree_icons/spacer_icon_folder.gif', // node icon selected
    'icon_24':'/images/tree_icons/spacer_icon_folder.gif', // node icon opened
    'icon_28':'/images/tree_icons/spacer_icon_folder.gif', // node icon selected opened
    'icon_80':'/images/tree_icons/spacer_icon_folder.gif', // mouseovered node icon normal
    'icon_88':'/images/tree_icons/spacer_icon_folder.gif',

/*
// icons - leaf
    'icon_0':'/images/tree_icons/spacer_icon_0.gif', // leaf icon normal (page.gif in demo3)
    'icon_4':'/images/tree_icons/spacer_icon_4.gif', // leaf icon selected
*/

// icons - junctions
    'icon_2':'/images/tree_icons/spacer_icon_2.gif', // junction for leaf (joinbottom.gif aus demo3)
    'icon_3':'/images/tree_icons/spacer_icon_3.gif',       // junction for last leaf (join aus demo3)
    'icon_18':'/images/tree_icons/plus.gif', // junction for closed node
    'icon_19':'/images/tree_icons/plus.gif',       // junctioin for last closed node
    'icon_26':'/images/tree_icons/minus.gif',// junction for opened node
    'icon_27':'/images/tree_icons/minus.gif',      // junctioin for last opended node

// icons - misc
    'icon_e':'/images/tree_icons/spacer_icon_e.gif', // empty image
    'icon_l':'/images/tree_icons/spacer_icon_l.gif',  // vertical line (empty.gif in demo3)

// styles - root
    'style_48':'mout', // normal root caption style
    'style_52':'mout', // selected root caption style
    'style_56':'mout', // opened root caption style
    'style_60':'mout', // selected opened root caption style
    'style_112':'mover', // mouseovered normal root caption style
    'style_116':'mover', // mouseovered selected root caption style
    'style_120':'mover', // mouseovered opened root caption style
    'style_124':'mover', // mouseovered selected opened root caption style

// styles - node
    'style_16':'node', // normal node caption style
    'style_20':'node_selected', // selected node caption style
    'style_24':'node', // opened node caption style
    'style_28':'node_selected', // selected opened node caption style
    'style_80':'node_hover', // mouseovered normal node caption style
    'style_84':'node_selected_hover', // mouseovered selected node caption style
    'style_88':'node_hover', // mouseovered opened node caption style
    'style_92':'node_selected_hover', // mouseovered selected opened node caption style

// styles - leaf
    'style_0':'file', // normal leaf caption style
    'style_4':'file_selected', // selected leaf caption style
    'style_64':'file_hover', // mouseovered normal leaf caption style
    'style_68':'file_selected_hover', // mouseovered selected leaf caption style


//    'onItemSelect':'onItemSelectHandler',

    'onItemOpen':'onItemOpenHandler'

    // make sure there is no comma after the last key-value pair

};

/*
    one two check
*/
function helloWorld()
{
    alert('helloWorld');
    return true;
}

/*
	This function modifies tree behavior so only one item
	in a level can be opened at a time.
	Function should be called AS onItemOpen handler of the tree
*/
function onItemOpenHandler(o_item)
{
    // get current block
    var a_curblock = o_item.o_parent.a_children;

    // close all nodes except current
    for (var i = 0; i < a_curblock.length; i++)
        if (a_curblock[i].n_state & 48 && a_curblock[i] != o_item)
            a_curblock[i].open(true);

        // proceed to default handler
    return true;
}

/* expands nodes of the tree
	n_index - zero based index of the tree on the pate
                  if omitomittedn applied to first tree (n_index==0)
	n_depth - zero based level to which the tree with be expanded
	          if omitted the tree will be fully expanded
 */
function expand_all(n_index, n_depth)
{
    var o_tree = TREES[n_index ? n_index : 0];
    if (!o_tree)
        alert("Tree is not initialized yet");
    var a_nodes = o_tree.a_nodes;
    for (var i = 0; i < a_nodes.length; i++)
        if (n_depth == null || a_nodes[i].n_depth <= n_depth)
            a_nodes[i].open(0, 1);
    o_tree.ndom_refresh();
}

/* collapses nodes of the tree
	n_index - zero based index of the tree on the pate
                  if omited then applied to first tree (n_index==0)
	n_depth - zero based level to which the tree with be collapsed
	          if omitted the tree will collapsed to second level (n_depth==1)
 */
function collapse_all(n_index, n_depth)
{
    var o_tree = TREES[n_index ? n_index : 0];
    if (!n_depth) n_depth = 1;
    if (!o_tree)
        alert("Tree is not initialized yet");
    var a_nodes = o_tree.a_nodes;
    for (var i = a_nodes.length - 1; i >= 0; i--)
        if (a_nodes[i].n_depth >= n_depth && a_nodes[i].open)
            a_nodes[i].open(1, 1);
    o_tree.ndom_refresh();
}

/*
	This function modifies tree behavior so nodes without link
	assigned will open on single click
	Function should be called AS onItemSelect handler of the tree
*/

function onItemSelectHandler (o_item) {
    // if node with no link then toggle
    var o_state = o_item.state();
    if (o_state['node'] && !o_item.a_config[1]) {
        o_item.o_root.toggle(o_item.n_id);
        // cancel default action
        return false;
    }
    // proceed to default handler
    return true;
}