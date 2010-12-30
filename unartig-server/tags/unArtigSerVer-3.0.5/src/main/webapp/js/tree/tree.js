// Title: Tigra Tree Menu PRO
// URL: http://www.softcomplex.com/products/tigra_menu_tree_pro/
// Version: 1.2
// Date: 05-07-2004 (mm-dd-yyyy)
// Technical Support: support@softcomplex.com (specify product title and order ID)
// Notes: Registration needed to use this script legally.
// Visit official site for details.

function tree(tmp0, tmp1, tmp2) {
    var i;
    this.a_tpl = tmp1;
    this.a_config = tmp0;
    this.o_root = this;
    this.a_index = [];
    this.a_nodes = [];
    this.o_selected = 0;
    this.n_depth = -1;
    this.tmp3 = Boolean(tmp1['multiselections']);
    this.tmp4 = tmp1['cookie_ext']?tmp1['cookie_ext']:'';
    this.tmp5 = tmp1['b_solid'];
    this.tmp6 = tmp1['b_rootcollapse']?0:1;
    var tmp7 = new Image(),tmp8 = new Image();
    tmp7.src = tmp1['icon_e'];
    tmp8.src = tmp1['icon_l'];
    tmp1['im_e'] = tmp7;
    tmp1['im_l'] = tmp8;
    for (i = 0; i < 128; i++)if (tmp1['icon_' + i]) {
        var tmp9 = new Image();
        tmp1['im_' + i] = tmp9;
        tmp9.src = tmp1['icon_' + i]
    }
    this.toggle = function(n_id) {
        var tmpA = this.a_index[n_id];
        tmpA.open(tmpA.n_state & 8)
    };
    this.select = function(n_id) {
        if (this.tmp3) {
            var tmpA = this.a_index[n_id];
            return tmpA.select(tmpA.n_state & 4)
        }
        return this.a_index[n_id].select()
    };
    this.mover = function(n_id) {
        var tmpA = this.a_index[n_id];
        tmpA.tmpB();
        tmpA.mover(true)
    };
    this.mout = function(n_id) {
        var tmpA = this.a_index[n_id];
        tmpA.tmpB(true);
        tmpA.mout(true)
    };
    this.find_item = function(tmpC, tmpD) {
        var tmp0 = [];
        tmpD = tmpD?1:0;
        for (var i = 0; i < this.a_index.length; i++)if (this.a_index[i].a_config[tmpD] == tmpC) {
            tmp0[tmp0.length] = this.a_index[i]
        }
        return tmp0
    };
    this.find_item_by_key = function(tmpE, tmpC) {
        var tmp0 = [];
        for (var i = 0; i < this.a_index.length; i++)if (this.a_index[i].a_config[2] && this.a_index[i].a_config[2][tmpE] == tmpC) {
            tmp0[tmp0.length] = this.a_index[i]
        }
        return tmp0
    };
    this.find_item_by_state = function(tmpF) {
        var tmp0 = [];
        for (var i = 0; i < this.a_index.length; i++)if (this.a_index[i].n_state & tmpF) {
            tmp0[tmp0.length] = this.a_index[i]
        }
        return tmp0
    };
    this.tmpG = false;
    this.ndom_refresh = function() {
        if (!B_DOM && this.tmpG)window.location = window.location
    };
    this.reset_state = function() {
        document.cookie = 'tree_' + this.n_id + '_state=;' + this.tmp4;
        document.cookie = 'tree_' + this.n_id + '_selected=;' + this.tmp4;
        this.a_states = [];
        this.tmpH = []
    };
    this.a_children = [];
    for (i = 0; i < tmp0.length; i++)this.a_children[this.a_children.length] = new tmpI(this, i);
    B_DOM = Boolean(document.body && document.body.innerHTML);
    this.n_id = tmp2?tmp2:TREES.length;
    TREES[this.n_id] = this;
    this.tmpJ = function(tmpK, tmpE) {
        var tmpL = new RegExp('^\\s*(\\S+)\\s*=\\s*(\\S*)\\s*$'),tmpC;
        for (var i = 0; i < tmpK.length; i++) {
            if (!tmpL.exec(tmpK[i]) || RegExp.$1 != tmpE)continue;
            tmpC = RegExp.$2;
            return tmpC.split('_')
        }
        return[]
    };
    var tmpK = document.cookie.split(';'),tmpM = 'tree_' + this.n_id + '_state',tmpN = 'tree_' + this.n_id + '_selected';
    this.a_states = this.tmpJ(tmpK, tmpM);
    this.tmpH = this.tmpJ(tmpK, tmpN);
    if (!this.a_states.length)this.tmpO = true;
    if (tmp1['beforeInit']) {
        eval('var tmpP=' + tmp1['beforeInit'] + '(this);');
        if (!tmpP)return false
    }
    for (i = 0; i < this.a_children.length; i++) {
        this.a_children[i].tmpQ = tmpR;
        document.write(this.a_children[i].tmpQ())
    }
    if (tmp1['afterInit'])eval(tmp1['afterInit'] + '(this);')
}
function tmpI(o_parent, n_order) {
    this.o_root = o_parent.o_root;
    this.n_depth = o_parent.n_depth + 1;
    this.a_config = o_parent.a_config[n_order + (this.n_depth?3:0)];
    this.o_parent = o_parent;
    this.n_order = n_order;
    while (!this.a_config[this.a_config.length - 1])this.a_config.length = this.a_config.length - 1;
    this.n_id = this.o_root.a_index.length;
    this.o_root.a_index[this.n_id] = this;
    this.tmpS = (this.a_config[2]?this.a_config[2]['fn']:0);
    if (this.a_config.length < 4 && !this.tmpS)return;
    this.n_node_id = this.o_root.a_nodes.length;
    this.o_root.a_nodes[this.n_node_id] = this;
    for (var i = 3; i < this.a_config.length; i++)new tmpI(this, i - 3)
}
function tmpT(tmpU, tmpV) {
    if (Boolean(this.n_state & 8) != Boolean(tmpU))return;
    var tmpW = (this.a_config[2]?this.a_config[2][tmpU?'hc':'ho']:null);
    tmpW = (tmpW?tmpW:this.o_root.a_tpl[tmpU?'onItemClose':'onItemOpen']);
    if (tmpW) {
        eval('var tmpP=' + tmpW + '(this);');
        if (!tmpP)return false
    }
    this.n_state ^= 8;
    this.o_root.tmpG = true;
    this.state_lookup();
    this.tmpB();
    this.save();
    if (B_DOM) {
        if (this.a_config.length > 3) {
            var tmpX = tmpY('c' + this.o_root.n_id + '_' + this.n_id);
            if (!tmpX.innerHTML)tmpX.innerHTML = this.tmpZ();
            tmpX.style.display = (tmpU?'none':'block')
        }
    } else if (!tmpV && this.a_config.length > 3)window.location = window.location
}
function tmpa(tmpb) {
    var tmpW = (this.a_config[2]?this.a_config[2][tmpb?'hd':'hs']:null);
    tmpW = (tmpW?tmpW:this.o_root.a_tpl[tmpb?'onItemDeselect':'onItemSelect']);
    if (tmpW) {
        eval('var tmpP=' + tmpW + '(this);');
        if (!tmpP)return false
    }
    if (tmpb) {
        this.n_state &= ~4
    } else {
        if (!this.o_root.tmp3) {
            var tmpc = this.o_root.o_selected;
            if (tmpc)tmpc.select(true);
            this.o_root.o_selected = this
        }
        this.n_state |= 4
    }
    this.selsave();
    this.state_lookup();
    this.tmpB();
    return Boolean(this.a_config[1])
}
function tmpd() {
    var tmpW = (this.a_config[2]?this.a_config[2]['hv']:null);
    tmpW = (tmpW?tmpW:this.o_root.a_tpl['onItemMover']);
    if (tmpW) {
        eval('var tmpP=' + tmpW + '(this);');
        if (!tmpP)return false
    }
    this.n_state |= 64;
    this.state_lookup()
}
function tmpe() {
    var tmpW = (this.a_config[2]?this.a_config[2]['hu']:null);
    tmpW = (tmpW?tmpW:this.o_root.a_tpl['onItemMout']);
    if (tmpW) {
        eval('var tmpP=' + tmpW + '(this);');
        if (!tmpP)return false
    }
    this.n_state &= ~64;
    this.state_lookup()
}
function tmpf(tmpg) {
    window.setTimeout("window.status=unescape('" + (tmpg?'':(this.a_config[2] && this.a_config[2]['sb']?escape(this.a_config[2]['sb']):escape(this.a_config[0]) + (this.a_config[1]?' (' + escape(this.a_config[1]) + ')':''))) + "')", 10)
}
function tmpR() {
    var a_index = this.o_root.a_index,n_id = this.n_id,i;
    this.state_lookup = tmph;
    this.mover = tmpd;
    this.mout = tmpe;
    this.select = tmpa;
    this.selsave = tmpi;
    this.load = tmpj;
    this.tmpB = tmpf;
    var tmpk = [],tmpl = new RegExp("o_tree_item", "g");
    var tmpm = this.a_config[2]?1:0;
    tmpk[1] = (tmpm?this.a_config[2]['hie'] || this.o_root.a_tpl['userIconEvent']:this.o_root.a_tpl['userIconEvent']);
    tmpk[2] = (tmpm?this.a_config[2]['hte'] || this.o_root.a_tpl['userTextEvent']:this.o_root.a_tpl['userTextEvent']);
    if (this.a_config.length > 3 || this.tmpS) {
        tmpk[0] = (tmpm?this.a_config[2]['hje'] || this.o_root.a_tpl['userJoinEvent']:this.o_root.a_tpl['userJoinEvent']);
        this.a_children = [];
        while (true) {
            n_id++;
            if (n_id == a_index.length)break;
            if (a_index[n_id].n_depth <= this.n_depth)break;
            if (a_index[n_id].n_depth == this.n_depth + 1) {
                a_index[n_id].tmpQ = tmpR;
                this.a_children[this.a_children.length] = a_index[n_id]
            }
        }
        this.open = tmpT;
        this.save = tmpn;
        this.tmpZ = tmpo
    } else {
        this.open = function() {
        }
    }
    this.n_state = (this.n_depth?0:32) + (this.a_children || this.tmpS?16:0) + (this.n_order == this.o_parent.a_children.length - 1?1:0);
    var tmpp = (this.o_root.a_tpl['style_icons']?' class="' + this.o_root.a_tpl['style_icons'] + '"':''),tmpq = [],tmpr = this.o_parent,tmps = this.a_config[2];
    for (i = this.n_depth; i > this.o_root.tmp6; i--) {
        tmpq[i] = '<img src="' + this.o_root.a_tpl[tmpr.n_state & 33?'icon_e':'icon_l'] + '"' + tmpp + ' border="0">';
        tmpr = tmpr.o_parent
    }
    this.load();
    for (i in tmpk) {
        if (tmpk[i]) {
            tmpk[i] = tmpt(tmpk[i]);
            tmpk[i] = tmpk[i].replace(tmpl, 'TREES[\'' + this.o_root.n_id + '\'].a_index[' + this.n_id + ']')
        } else tmpk[i] = ''
    }
    var tmpu = this.state_lookup(true);
    return '<table cellpadding="0" cellspacing="0" border="0"><tr onmouseover="TREES[\'' + this.o_root.n_id + '\'].mover(' + this.n_id + ')" onmouseout="TREES[\'' + this.o_root.n_id + '\'].mout(' + this.n_id + ')"><td nowrap>' + tmpq.join('') + (tmpu[1]?(this.a_children?'<a href="javascript: TREES[\'' + this.o_root.n_id + '\'].toggle(' + this.n_id + ')" onclick = "if(this.blur)this.blur();TREES[\'' + this.o_root.n_id + '\'].toggle(' + this.n_id + ');return false;" onmouseover="TREES[\'' + this.o_root.n_id + '\'].mover(' + this.n_id + ')" onmouseout="TREES[\'' + this.o_root.n_id + '\'].mout(' + this.n_id + ')" ' + tmpk[0] + ' ><img src="' + tmpu[1] + '" border="0" name="j' + this.o_root.n_id + '_' + this.n_id + '"' + tmpp + '></a>':'<img src="' + tmpu[1] + '" border="0"' + tmpp + '>'):'') + (tmpu[0]?'<a href="' + this.a_config[1] + '" target="' + (tmps && tmps['tw']?tmps['tw']:this.o_root.a_tpl['target']) + '" title="' + (tmps && tmps['tt']?tmps['tt']:'') + '" onclick="if(this.blur)this.blur();return TREES[\'' + this.o_root.n_id + '\'].select(' + this.n_id + ')" ondblclick="TREES[\'' + this.o_root.n_id + '\'].' + (this.a_children?'toggle(':'select(') + this.n_id + ')" ' + tmpk[1] + '><img src="' + tmpu[0] + '" border="0" name="i' + this.o_root.n_id + '_' + this.n_id + '"' + tmpp + '></a>':'') + '</td><td nowrap' + (tmpu[2]?' class="' + tmpu[2] + '"':'') + ' id="t' + this.o_root.n_id + '_' + this.n_id + '"><a href="' + this.a_config[1] + '" target="' + (tmps && tmps['tw']?tmps['tw']:this.o_root.a_tpl['target']) + '" title="' + (tmps && tmps['tt']?tmps['tt']:'') + '" onclick="if(this.blur)this.blur();return TREES[\'' + this.o_root.n_id + '\'].select(' + this.n_id + ')" ondblclick="TREES[\'' + this.o_root.n_id + '\'].' + (this.a_children?'toggle(':'select(') + this.n_id + ')" ' + tmpk[2] + '>' + this.a_config[0] + '</a></td></tr></table>' + (this.a_children?'<div id="c' + this.o_root.n_id + '_' + this.n_id + '" style="display:' + (this.n_state & 8?'block">' + this.tmpZ():'none">' + (this.o_root.tmp5?this.tmpZ():'')) + '</div>':'')
}
function tmpo() {
    var tmpv = [];
    for (var i = 0; i < this.a_children.length; i++)tmpv[i] = this.a_children[i].tmpQ();
    return tmpv.join('')
}
function tmpn() {
    var tmpw = Math.floor(this.n_node_id / 31);
    this.o_root.a_states[tmpw] = (this.n_state & 8?this.o_root.a_states[tmpw] | (1 << (this.n_node_id % 31)):this.o_root.a_states[tmpw] & ~(1 << (this.n_node_id % 31)));
    document.cookie = 'tree_' + this.o_root.n_id + '_state=' + this.o_root.a_states.join('_') + ';' + this.o_root.tmp4
}
function tmpi() {
    var tmpw = Math.floor(this.n_id / 31);
    this.o_root.tmpH[tmpw] = (this.n_state & 4?this.o_root.tmpH[tmpw] | (1 << (this.n_id % 31)):this.o_root.tmpH[tmpw] & ~(1 << (this.n_id % 31)));
    document.cookie = 'tree_' + this.o_root.n_id + '_selected=' + this.o_root.tmpH.join('_') + ';' + this.o_root.tmp4
}
function tmpj() {
    var tmpW = (tmpW?tmpW:this.o_root.a_tpl['onItemLoad']);
    if (tmpW) {
        eval('var tmpP=' + tmpW + '(this);');
        if (!tmpP)return false
    }
    if (this.a_config[2] && typeof(this.a_config[2]['sl']) == 'number') {
        if (Boolean(this.a_config[2]['sl'])) {
            if (!this.o_root.tmp3) {
                if (this.o_root.o_selected)this.o_root.o_selected.n_state &= ~4;
                this.o_root.o_selected = this
            }
            this.n_state |= 4
        } else this.n_state &= ~4
    } else {
        var tmpw = Math.floor(this.n_id / 31);
        if (Boolean(this.o_root.tmpH[tmpw] & (1 << (this.n_id % 31)))) {
            if (!this.o_root.tmp3) {
                if (this.o_root.o_selected)this.o_root.o_selected.n_state &= ~4;
                this.o_root.o_selected = this
            }
            this.n_state |= 4
        } else this.n_state &= ~4
    }
    if (!this.a_children)return;
    if (this.a_config[2] && typeof(this.a_config[2]['st']) == 'number') {
        if (Boolean(this.a_config[2]['st']))this.n_state |= 8; else this.n_state &= ~8;
        return
    }
    if (this.o_root.tmpO && !this.n_depth) {
        this.n_state |= 8;
        this.save();
        return
    }
    var tmpw = Math.floor(this.n_node_id / 31);
    if (Boolean(this.o_root.a_states[tmpw] & (1 << (this.n_node_id % 31))))this.n_state |= 8; else this.n_state &= ~8
}
function tmph(tmpx) {
    var tmpy = this.n_state & ~3;
    var tmpz = this.n_state & ~68 | 2;
    var tmp00 = this.a_config[2]?this.a_config[2]['i' + (tmpy & ~48)]:0;
    if (!tmp00)tmp00 = this.o_root.a_tpl['icon_' + tmpy];
    if (!tmp00)tmp00 = this.o_root.a_tpl['icon_' + (tmpy & ~64)];
    var tmp01 = this.o_root.a_tpl['icon_' + tmpz];
    var tmp02 = this.a_config[2]?this.a_config[2]['s' + (tmpy & ~48)]:0;
    if (!tmp02)tmp02 = this.o_root.a_tpl['style_' + tmpy];
    if (!tmp02)tmp02 = this.o_root.a_tpl['style_' + (tmpy & ~64)];
    if (tmpx)return[tmp00,tmp01,tmp02];
    var tmp03 = document.images['j' + this.o_root.n_id + '_' + this.n_id];
    if (tmp03)tmp03.src = tmp01;
    tmp03 = document.images['i' + this.o_root.n_id + '_' + this.n_id];
    if (tmp03)tmp03.src = tmp00;
    tmp03 = tmpY('t' + this.o_root.n_id + '_' + this.n_id);
    if (tmp03)tmp03.className = tmp02
}
function tmpt(tmpk) {
    var tmp04 = [];
    for (var tmpE in tmpk)tmp04[tmp04.length] = tmpE + '="' + tmpk[tmpE] + '"';
    return tmp04.join(' ')
}
var TREES = [],B_DOM;
tmpY = document.all?function(tmp05) {
    return document.all[tmp05]
}:(document.getElementById?function(tmp05) {
    return document.getElementById(tmp05)
}:function(tmp05) {
    return null
});