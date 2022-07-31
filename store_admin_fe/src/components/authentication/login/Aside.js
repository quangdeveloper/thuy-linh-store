import styled from 'styled-components';
import {Link} from 'react-router-dom';

const Wrapper = styled.div`
    background: url('assets/media/bg/mask_group_1.jpg') no-repeat;  
`;

const Aside = () => (
    <Wrapper className="login-aside d-flex flex-column flex-row-auto">					
		<div className="d-flex flex-column-auto flex-column pt-lg-40 pt-15">
          <Link to="https://vnpay.vn/" className="text-center mb-10">
            {/*<img src="assets/media/logos/logoVNPAY.png" className="max-h-70px" alt="" />*/}
          </Link>
        </div>
	</Wrapper>
)

export default Aside