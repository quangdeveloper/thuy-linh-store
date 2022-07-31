import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
.descending::after{
    content: "\f063";
    font-family: 'Line Awesome Free';
    font-weight: 900;
  }
  .ascending::after{
    content: "\f062";
    font-family: 'Line Awesome Free';
    font-weight: 900;
  }
  .descending, .ascending {
      color: #005BA9
  }
  .cursor-button {
      cursor: pointer
  }
`;

export default GlobalStyle;