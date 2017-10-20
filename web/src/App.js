import React, { Component } from 'react';

class App extends Component {

  componentWillMount() {

  }

  login() {
    
  }

  render() {
    return (
      <div>
        <p>Yo what is up!!</p>
        <button onClick={this.login()}>
          Login
        </button>
      </div>
    );
  }
}

export default App;
