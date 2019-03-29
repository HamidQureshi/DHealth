import { Activity, Standard } from "@activeledger/activecontracts";


/**
 * UpdateUser Smart Contract
 *
 * @export
 * @class UpdateUser
 * @extends {Standard}
 */
export default class UpdateUser extends Standard {
  /**
   * Holds the Activity stream as created or fetched
   *
   * @private
   * @type {Activity}
   * @memberof UpdateUser
   */
  private activity: Activity;

  /**
   * The input stream id provided in the transaction
   *
   * @private
   * @type {string}
   * @memberof UpdateUser
   */
  private inputStream: string;
  private outputStream: string;
  private stream: string;



  /**
   * The data provided in the transaction
   *
   * @private
   * @type {unknown}
   * @memberof UpdateUser
   */
  private data: any;

  /**
   * The current data state of the stream
   *
   * @private
   * @type {unknown}
   * @memberof UpdateUser
   */
  private state: any;


  public verify(selfsigned: boolean): Promise<boolean> {
    return new Promise<boolean>((resolve, reject) => {
       if (selfsigned) {
         
         if (this.transactions.$entry) {
          switch (this.transactions.$entry) {
            case "create":
              this.verifyCreate(resolve, reject);
              break;
            case "update":
              this.verifyUpdate(resolve, reject);
              break;
            default:
              reject("Entry not found.");
              break;
          }
        } else {
          reject("No entry found");
        }
      } else {
        reject("Identity Signatures Needed");
      }
    });
  }


  public vote(): Promise<boolean> {
    return new Promise<boolean>((resolve, reject) => {
      // Get Stream Activity and its state
      // Passed input verification so now get the Activity stream
      
   

      switch (this.transactions.$entry) {
        case "create":
          this.voteCreate(resolve, reject);
          break;
        case "update":
          this.voteUpdate(resolve, reject);
          break;
      }
    });
  }


  public commit(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      // Update Activity Streams
      // Create New Activity Streams
      switch (this.transactions.$entry) {
        case "create":
          this.commitCreate(resolve, reject);
          break;
        case "update":
          this.commitUpdate(resolve, reject);
          break;
      }
    });
  }


  private verifyCreate(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void):
 void {

      // Get the streams
        this.stream = Object.keys(this.transactions.$i)[0];
        this.data = this.transactions.$i[this.stream];
        resolve(true);
    }

  private verifyUpdate(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void):
 void {
     this.stream = Object.keys(this.transactions.$o)[0];
     this.data = this.transactions.$o[this.stream];
    resolve(true);
  }


  private voteCreate(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void):
 void {
        resolve(true);
      }

  private voteUpdate(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void):
 void {
         resolve(true);
      }


  private commitCreate(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void):
 void {
    
     this.activity = this.newActivityStream(this.stream);
     
     const namespace = this.transactions.$namespace;
   //const activity = this.newActivityStream(this.data.first_name);
     this.activity.setAuthority(
     this.transactions.$i[this.stream].publicKey,
     this.transactions.$i[this.stream].type   );

     const state = this.activity.getState();
     state.owner = this.stream;
     state.name = this.data.name;
    
     state.type = `${namespace}.${this.data.profile_type}`;
          //setting profile data
     state.first_name = this.data.first_name;
     state.last_name = this.data.last_name;
     state.email = this.data.email;
     state.date_of_birth =this.data. date_of_birth;
     state.phone_number =this.data. phone_number;
     state.address = this.data.address;
     state.security = this.data.security;
     state.profile_type = this.data.profile_type;
     state.gender = this.data.gender;
     state.dp =this.data. dp;
     this.activity.setState(state);

    resolve(true);
  }

  private commitUpdate(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void):
 void {
    this.activity = this.getActivityStreams(this.stream);
    this.state = this.activity.getState();
    this.activity.setState({...this.state, ...this.data});
  
    resolve(true);
  }

}