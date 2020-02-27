/**
 *
 * @Date 2019-02-22 21:32:00
 * @author chenliangfang
 *
 ***/


/************  start **************/
/************  end ***************/


/************ 初始化日期/时间 start **************/
laydateInt()
function laydateInt() {
    lay('.laydate').each(function () {
        laydate.render({
            elem: this,
            trigger: 'click',
            type: 'date',
            theme: '#007bff',
            done: function (value, date) {
                isStartEndDatetime(value, this.elem);
            }
        });

    });
    lay('.laydatetime').each(function () {
        laydate.render({
            elem: this,
            trigger: 'click',
            type: 'datetime',
            theme: '#007bff',
            done: function (value, date) {
                isStartEndDatetime(value, this.elem);
            }
        });
    });
}
//始结束时间对比
function isStartEndDatetime(date, el){
    let start = new Date($('.laystart').val());
    let end = new Date($('.layend').val());
    if ($(el).attr('class').indexOf('laystart')>-1 && end) {
        if (moment(date).isSameOrAfter(end)) {
            bs4pop.alert('结束时间需大于开始时间',{} ,function () {$(el).val('')});
        }
    } else if (start && $(el).attr('class').indexOf('layend')>-1 ) {
        if (moment(start).isSameOrAfter(date)) {
            bs4pop.alert('结束时间需大于开始时间',{} ,function () {$(el).val('')});
        }
    }
}
/************ 初始化日期/时间 end **************/


/************ HTML反转义 start **************/
function HTMLDecode(str) {
    var s = "";
    if (str.length == 0) return "";
    s = str.replace(/&amp;/g, "&");
    s = s.replace(/&lt;/g, "<");
    s = s.replace(/&gt;/g, ">");
    s = s.replace(/&nbsp;/g, " ");
    s = s.replace(/&#39;/g, "\'");
    s = s.replace(/&quot;/g, "\"");
    s = s.replace(/<br\/>/g, "\n");
    return s;
}
//获取table Index
function getIndex(str) {
    return str.split('_')[1];
}
/************ HTML反转义 end **************/
