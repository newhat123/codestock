/*
 * 此函数用于构建包含参数对的url
 * 并负责对参数内容进行编码，否则会产生乱码。
 * pnames是参数列表，必须对应后台类的属性名
 * 参数列表中的值前面加ed是编辑控件的id
 * 通过编辑控件的id获取值。
 * 此函数如果写在html中，&符号会导致无法运行。
 */
function Csurl(url, pnames) {
	for (var i = 0; i < pnames.length; i++) {
		var edval;
		if ($("#ed" + pnames[i]).hasClass("selectpicker")) {
			edval = $("#ed" + pnames[i]).selectpicker('val');
		} else {
			edval = $("#ed" + pnames[i]).val();
		}
		edval = encodeURIComponent(edval);
		url += "&" + pnames[i] + "=" + edval;
	}
	return url;
}
