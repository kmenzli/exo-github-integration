(function ($) {

  var apiURL = 'https://api.github.com/repos/kmenzli/sphinx-poc/commits?per_page=3&sha='

  /**
   * Actual demo
   */

  var demo = new Vue({

    el: '#github',

    data: {
      branches: ['master', 'stable/4.4.x'],
      currentBranch: 'master',
      commits: null
    },

    created: function () {
      this.usingJuzu()
    },

    watch: {
      currentBranch: 'usingJuzu'
    },

    filters: {
      truncate: function (v) {
        var newline = v.indexOf('\n')
        return newline > 0 ? v.slice(0, newline) : v
      },
      formatDate: function (v) {
        return v.replace(/T|Z/g, ' ')
      }
    },

    methods: {
      fetchData: function () {
        var xhr = new XMLHttpRequest()
        var self = this
        xhr.open('GET', apiURL + self.currentBranch)
        xhr.onload = function () {
          self.commits = JSON.parse(xhr.responseText)
          console.log(self.commits[0].html_url)
        }
        xhr.send()
      },
      usingJuzu: function () {

        var $githubDiv = $("#github");
        var createURL = $githubDiv.jzURL("GithubIntegrationController.create");
        $.ajax({
          type: 'POST',
          url: createURL,
          success: function (data) {
            // Reload project tree;
            this.commits = []
            console.log(data.id);
          },
          error: function (xhr) {
            if (xhr.status >= 400) {
              console.log(xhr.responseText);
            } else {
              alert('error while create new project. Please try again.');
            }
          }
        });

      }
    }
  })
})(jQuery);