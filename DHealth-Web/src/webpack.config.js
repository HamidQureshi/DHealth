module.exports = {
    node: {
      crypto: true,
      fs: 'empty'
    },
    module: {
      loaders: [
        {
          test: /\.json$/,
          loader: 'json-loader'
        }
      ]
    }
  };