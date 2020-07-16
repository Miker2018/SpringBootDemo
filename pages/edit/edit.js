// pages/edit/edit.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    areaId: undefined,
    areaName: '',
    priority: '',

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (param) {
    var that = this;
    if (param.areaId == undefined)  //区域添加操作，不需要加载数据
      return
    //否则为区域修改操作
    this.setData({
      areaId: param.areaId
    })
    wx.request({
      url: 'http://localhost:8080/SpringBootDemo/area/getById',
      data: { "areaId": this.data.areaId },
      method: 'GET',
      success: (res) => {
        var area = res.data.area;
        if (area == undefined) {
          wx.showToast({
            title: "数据获取失败",
            icon: '',
            duration: 2000
          });
        } else {
          that.setData({
            areaName: area.areaName,
            priority: area.priority
          })
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  submitForm: function (e) {
    var that = this;
    var formData = e.detail.value;    //获取表单中的值
    var url;
    if (that.data.areaId != undefined) {    //areaId不为空，为修改area信息的操作
      formData.areaId = that.data.areaId;
      url = "http://localhost:8080/SpringBootDemo/area/update";
    } else {    //否则为添加area操作
      url = "http://localhost:8080/SpringBootDemo/area/add";
    }
    wx.request({
      url: url,
      method: 'POST',
      data: JSON.stringify(formData),
      header: {
        'Content-Type': 'application/json'
      },
      success: (res) => {
        if (res.data.success) {
          wx.showToast({
            title: '操作成功',
            duration:2000
          });
          wx.redirectTo({
            url: '../area/area',
          })
        } else {
          wx.showToast({
            title: '操作失败' + res.data.errMsg,
          })
        }
      }
    })
  }
})