/*!
 * blogedit.html 页面脚本.
 * 
 * @since: 1.0.0 2017-03-26
 * @author Way Lau <https://waylau.com>
 */
"use strict";
//# sourceURL=blogedit.js

// DOM 加载完再执行
$(function() {
	
	// 初始化 md 编辑器
    $("#md").markdown({
        language: 'zh',
        fullscreen: {
            enable: true
        },
        resize:'vertical',
        localStorage:'md'
    });
        
    $('.form-control-chosen').chosen();

    //初始化标签
    $('.form-control-tag').tagsInput({
        'defaultText':'输入标签',
        'height':'100px', //设置高度
        'width':'300px',  //设置宽度
        'interactive':true, //是否允许添加标签，false为阻止
        'removeWithBackspace' : true, //是否允许使用退格键删除前面的标签，false为阻止
        'delimiter': ', ',
        'minChars' : 0, //每个标签的小最字符
        'maxChars' : 0 ,//每个标签的最大字符，如果不设置或者为0，就是无限大
        'placeholderColor' : '#666666' //设置defaultText的颜色
    });


    $("#uploadImage").click(function() {
        $.ajax({
            url: '/fileserver/upload',
            type: 'POST',
            cache: false,
            data: new FormData($('#uploadformid')[0]),
            processData: false,
            contentType: false,
            success: function(data){
                var mdcontent=$("#md").val();
                 $("#md").val(mdcontent + "\n![]("+data +") \n");

             }
        }).done(function(res) {
            $('#file').val('');
        }).fail(function(res) {});
    })

    // 发布博客
    $("#submitBlog").click(function() {

        // 获取 CSRF Token 
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            url: '/blog/'+ $(this).attr("userName") + '/edit',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify({"id":$('#blogId').val(), 
                "title": $('#title').val(), 
                "summary": $('#summary').val() , 
                "content": $('#md').val(), 
                "catalog":{"id":$('#catalogSelect').val()},
                "tags":$('.form-control-tag').val()
                }),
            // beforeSend: function(request) {
            //     request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            // },
             success: function(data){
                 if (data.code == 1) {
                    // 成功后，重定向
                     window.location = data.data;
                 } else if(data.code == -1){
                     var node = data.data;
                     for (var x in node) { // 遍历Map
                         toastr.error(node[x])
                     }
                 }else{
                     toastr.error("error!"+data.message);
                 }

             },
             error : function(data) {
                alert(data.code);
                 toastr.error("error!");
             }
        })
    });
    // // 初始化标签控件
    // $('.form-control-tag').tagEditor({
    //     initialTags: [],
    //     maxTags: 5,
    //     delimiter: ', ',
    //     forceLowercase: false,
    //     animateDelete: 0,
    //     placeholder: '请输入标签'
    // });


});