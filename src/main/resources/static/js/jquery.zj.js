$.fn.extend({
    count: function (maxLength) {
      var self = this;
      $(self).attr("maxlength", maxLength);
      var tips=$("<span></span>")
      self.after(tips);
      show();
      $(self).on("input propertychange", show);
      function show() {
        tips.text($(self).val().length + "/" + maxLength);
      }
    }
})