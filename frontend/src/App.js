import './App.css';
import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Route, Routes, Switch } from 'react-router-dom';
import Sort from "./Sort";

function App() {

  return (
      <Router>
        <Routes>
          <Route exact path="/" element={<Sort/>}/>
        </Routes>
      </Router>
  );
}

export default App;
