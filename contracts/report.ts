import { Activity, Standard } from "@activeledger/activecontracts";

interface Report{
  description:string;
  title:string;
  fileName:string;
  patientName:string;
  content:string;
  doctors:string[];

}
/**
 * UpdateUser Smart Contract
 *
 * @export
 * @class UpdateUser
 * @extends {Standard}
 */
export default class ReportContract extends Standard {
  /**
   * Holds the Activity stream as created or fetched
   *
   * @private
   * @type {Activity}
   * @memberof UpdateUser
   */
  private activity: Activity;
    private reportsActivity: Activity;

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
  private reports: Report[];
  



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
       if (!selfsigned) {
         
         if (this.transactions.$entry) {
          switch (this.transactions.$entry) {
            case "upload":
              this.verifyUpload(resolve, reject);
              break;
            case "remove":
              this.verifyRemove(resolve, reject);
              break;
            case "update":
              this.verifyUpdate(resolve, reject);
              break;
            case "retain":
              this.verifyRetain(resolve, reject);
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
        case "upload":
          this.voteUpload(resolve, reject);
          break;
        case "remove":
          this.voteRemove(resolve, reject);
          break;
        case "update":
          this.voteUpdate(resolve, reject);
          break;
        case "retain":
          this.voteRetain(resolve, reject);
          break;
      }
    });
  }


  public commit(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      // Update Activity Streams
      // Create New Activity Streams
      
      switch (this.transactions.$entry) {
        case "upload":
        
          this.commitUpload(resolve, reject);
          break;
        case "remove":
          this.commitRemove(resolve, reject);
          break;
        case "update":
          this.commitUpdate(resolve, reject);
          break;
        case "retain":
          this.commitRetain(resolve, reject);
          break;
      }
    });
  }


  private verifyUpload(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void):
 void {

      // Get the streams
        this.stream = Object.keys(this.transactions.$o)[0];
        this.data = this.transactions.$o[this.stream];
       

        resolve(true);
    }

  private verifyRemove(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void):
 void {
     this.stream = Object.keys(this.transactions.$o)[0];
     this.data = this.transactions.$o[this.stream];
    resolve(true);
  }

 private verifyUpdate(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void
  ): void {
     this.stream = Object.keys(this.transactions.$o)[0];
     this.data = this.transactions.$o[this.stream];
     this.activity = this.getActivityStreams(this.stream);
    this.state = this.activity.getState();
    resolve(true);
//  for(let reportObject of this.data)
//       {
//         for(let userReport of this.state.reports)
//         {
//           if(userReport.content=="")
//            continue;
//            if(userReport.content==reportObject.content)
//            {
//              resolve(true);
//            }
//         }
//        //this.state.reports.push(report);
//      }
   // if (this.data.stream && this.data.stream.length === 64) {
    //  reject("Report invalid");
    //} else {
     // reject("No recipient provided");
    //}
  }
private verifyRetain(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void
  ): void {
     this.stream = Object.keys(this.transactions.$o)[0];
     this.data = this.transactions.$o[this.stream];

    if (this.data.stream && this.data.stream.length === 64) {
      resolve(true);
    } else {
      reject("No recipient provided");
    }
  }



  private voteUpload(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void):
 void {
   
        resolve(true);
      }

  private voteRemove(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void):
 void {
         resolve(true);
      }
 private voteUpdate(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void
  ): void {
   
      resolve(true);
    
  }

  private voteRetain(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void
  ): void {
   
      resolve(true);
    
  }

  private commitUpload(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void):
 void {
    
     this.activity = this.getActivityStreams(this.stream);
         const namespace = this.transactions.$namespace;
      
       
     const state = this.activity.getState();
  

    for(let reportObject of this.data)
     {

    var report:Report= { content: '',doctors:[] ,description:'',title:'',fileName:'',patientName:''};
       report.content=reportObject.content;
      report.description=reportObject.description;
      report.fileName=reportObject.fileName;
      report.title=reportObject.title;
      report.patientName=reportObject.patientName;
    

      
       for(let doctor of reportObject.doctors)
            {
              report.doctors.push(doctor);
            }
    
      state.reports.push(report);
      let reportsActivity=this.newActivityStream(this.stream+".report");
      const newState =reportsActivity.getState();
      newState.report=report;
      newState.patientId=this.stream;
      reportsActivity.setState(newState);
       }
  
     this.activity.setState(state);

    resolve(true);
  }

  private commitRemove(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void):
 void {

   
    resolve(true);
  }


 private commitUpdate(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void
  ): void {
    this.activity = this.getActivityStreams(this.stream);
    this.state = this.activity.getState();
    
      for(let reportObject of this.data)
     {
       for(let userReport of this.state.reports)
       {
          if(userReport.content==reportObject.content)
          {
             for(let doctor of reportObject.doctors)
            {
              userReport.doctors.push(doctor);
            }
          }
       }
       //this.state.reports.push(report);
      
  
     }
    this.activity.setState(this.state);
    resolve(true);
  }



  private commitRetain(
    resolve: (ok: boolean) => void,
    reject: (message: string) => void
  ): void {
    
    resolve(true);
  }
}  

